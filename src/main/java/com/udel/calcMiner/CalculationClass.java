package com.udel.calcMiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.udel.dataFromIAK.CostParameter;
import com.udel.dataMiner.dataModel.Item;
import com.udel.dataMiner.dataModel.Line;
import com.udel.dataMiner.dataModel.Plant;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.CostAdKoef;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.OnlyCost;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.NaturalAdProcent;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.OnlyProcent;

public class CalculationClass {
    private Map<Integer, Object> IakData;
    private Map<String, Object> ParsedData;
    private Map<Integer ,Map<String, Double>> ItemsAndCosts = new HashMap<>();

    public CalculationClass(Map<Integer, Object> IakData, Map<String, Object> ParsedData){
        this.IakData = IakData;
        this.ParsedData = ParsedData;
    }

    public Map<Integer ,Map<String, Double>> StartAllCalculations(){
        Set<Integer> KeysFromIak = IakData.keySet();
        List<String> KeysFromParsedData = new ArrayList<>();
        List<String> ItemsList = new ArrayList<>();
        List<Double> NaturaList = new ArrayList<>();
        List<Double> CostsList = new ArrayList<>();
        int line_count = 0;

        for (String key : ParsedData.keySet()) {
            KeysFromParsedData.add(key);
        }

        List<Plant> ParsedPlants = (List<Plant>) ParsedData.get(KeysFromParsedData.get(KeysFromParsedData.size() - 1));
        
        for (Integer IakKey : KeysFromIak) {
            List<CostParameter> Params = (List<CostParameter>) IakData.get(IakKey);
            for (Plant Plant : ParsedPlants) {
                if (Plant.Id == IakKey) {
                    for (Item Item : Plant.Items) {
                        switch (Item.NaturalCalc){
                            case NaturalAndProcent:
                                ItemsList.add(Plant.Name + " " +Item.Name);
                                NaturaList.add(NaturalAdProcent(Plant, Item, Params.get(0),(List<NaturalAdProcent>) ParsedData.get("Натуральная показатель на процент")));
                                break;
                            case OnlyProcent:
                                ItemsList.add(Plant.Name + " " +Item.Name);
                                NaturaList.add( Params.get(0).general.getGnsForHeat() * OnlyProcent(Plant, Item, (List<OnlyProcent>) ParsedData.get("Только процентный показатель")));
                                break;
                            case Natural:
                                //ItemsList.add(Plant.Name + " " +Item.Name);
                                //CostsList.add(e);
                                break;
                            default:
                                throw new AssertionError();
                        }
                        switch (Item.CostCalc) {
                            case CostAndKoef:
                                CostsList.add(CostAdKoef(Plant, Params.get(0) ,Item, (List<CostAdKoef>) ParsedData.get("Стоимость на коеффициент")));
                                break;
                            case OnlyCost:
                                CostsList.add(OnlyCost(Plant, Item, (List<OnlyCost>) ParsedData.get("Только стоимость")));
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                    for (Line Line : Plant.Lines) {
                        for (Item Item : Line.Items) {
                            switch (Item.NaturalCalc){
                                case NaturalAndProcent:
                                    ItemsList.add(Line.Description + " " + Item.Name);
                                    NaturaList.add(NaturalAdProcent(Line, Params.get(line_count),Item, (List<NaturalAdProcent>) ParsedData.get("Натуральная показатель на процент")));
                                    break;
                                case OnlyProcent:
                                    //ItemsList.add(Line.Description + " " + Item.Name);
                                    //CostsList
                                    break;
                                case Natural:
                                    ItemsList.add(Line.Description + " " + Item.Name);
                                    NaturaList.add( Params.get(line_count).enter.getEnterFlows().get(0));
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            switch (Item.CostCalc) {
                                case CostAndKoef:
                                    CostsList.add(CostAdKoef(Line, Params.get(line_count), Item, (List<CostAdKoef>) ParsedData.get("Стоимость на коеффициент")));
                                    break;
                                case OnlyCost:
                                    CostsList.add(OnlyCost(Line, Params.get(line_count), Item, (List<OnlyCost>) ParsedData.get("Только стоимость")));
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                        }
                        line_count++;
                    }
                    ItemsAndCosts.put(IakKey, new HashMap<>());
                    Map<String, Double> tempItems = new HashMap<>();
                    for (int i = 0; i < ItemsList.size(); i++) 
                        tempItems.put(ItemsList.get(i),  NaturaList.get(i) * CostsList.get(i));
                    ItemsAndCosts.get(IakKey).putAll(tempItems);
                }
                line_count = 0;
            }
        }
        return ItemsAndCosts;
    }

    @Override
    public String toString(){
        String output = "";
        for (Integer key : ItemsAndCosts.keySet()) {
            output += "id - " + key + "\n";
            for (String ItemKey : ItemsAndCosts.get(key).keySet())
                output += "\t " + ItemKey + " - " + ItemsAndCosts.get(key).get(ItemKey) + "\n";
            
        }
        return output;
    }

    private Double NaturalAdProcent(Plant Plant, Item Item, CostParameter Param,List<NaturalAdProcent> NaturalAdProcents){
        for (NaturalAdProcent oneNatur : NaturalAdProcents) {
            if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Plant.Name + " " + Param.general.getModelMonthNumber()))
                return oneNatur.Natural * oneNatur.Procent;
        }
        return 0.0;
    }

    private Double NaturalAdProcent(Line Line, CostParameter Param, Item Item, List<NaturalAdProcent> NaturalAdProcents){
        for (NaturalAdProcent oneNatur : NaturalAdProcents) {
            if(Param.general.getIsCostCenterActive()){
                if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(Param.general.getCostCenterState()).Name + " " + Param.general.getModelMonthNumber())){
                        return oneNatur.Natural * oneNatur.Procent;
                }
            }
            else{
                if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Line.Description + " " + "Ремонт" + " " + Param.general.getModelMonthNumber())){
                    return oneNatur.Natural * oneNatur.Procent;
                }
            }
        }
        return 0.0;
    }

    private Double OnlyProcent(Plant Plant, Item Item, List<OnlyProcent> OnlyProcents){
        for (OnlyProcent oneProcent : OnlyProcents) {
            if(oneProcent.Nmae.equals(Item.Name) && oneProcent.Description.equals(Plant.Name))
                return oneProcent.Procent;
        }
        return 0.0;
    }

    private Double OnlyProcent(Line Line, Item Item, List<OnlyProcent> OnlyProcents){
        for (OnlyProcent oneProcent : OnlyProcents) {
            
        }
        return 0.0;
    }


    private double Natural(Plant Plant, Item Item, double NaturalParam){
        
        return 0;
    }

    private Double CostAdKoef(Plant Plant, CostParameter Param, Item Item, List<CostAdKoef> CostAdKoefs){
        for (CostAdKoef cost : CostAdKoefs) {
            if(cost.Name.equals(Item.Name) && cost.Description.equals(Plant.Name + " " + Param.general.getModelMonthNumber()))
                return cost.Cost * cost.Koef;
        }
        return 0.0;
    }

    private Double CostAdKoef(Line Line, CostParameter Param, Item Item, List<CostAdKoef> CostAdKoefs){
        for (CostAdKoef cost : CostAdKoefs) {
            if(Param.general.getIsCostCenterActive()){
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(Param.general.getCostCenterState()).Name + " " + Param.general.getModelMonthNumber()))
                    return cost.Cost * cost.Koef;
                    
            }
            else{
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Ремонт" + " " + Param.general.getModelMonthNumber()))
                    return cost.Cost * cost.Koef;    
            }
        }
        return 0.0;
    }

    private Double OnlyCost(Plant Plant, Item Item, List<OnlyCost> OnlyCosts){
        for (OnlyCost cost : OnlyCosts) {
            if(cost.Name.equals(Item.Name) && cost.Description.equals(Plant.Name))
                return cost.Cost;
        }
        return 0.0;
    }

    private Double OnlyCost(Line Line, CostParameter Param, Item Item, List<OnlyCost> OnlyCosts){
        for (OnlyCost cost : OnlyCosts) {
            if(Param.general.getIsCostCenterActive()){
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(Param.general.getCostCenterState()).Name))
                        return cost.Cost;
            }
            else{
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Ремонт"))
                    return cost.Cost;
            }
        }
        return 0.0;
    }

}
