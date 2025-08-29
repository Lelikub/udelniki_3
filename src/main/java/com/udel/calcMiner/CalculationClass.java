package com.udel.calcMiner;

import java.util.*;

import com.udel.dataFromIAK.CostParameter;
import com.udel.dataMiner.dataModel.Item;
import com.udel.dataMiner.dataModel.Line;
import com.udel.dataMiner.dataModel.Plant;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.CostAdKoef;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.OnlyCost;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.NaturalAdProcent;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.OnlyProcent;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectPlant;

public class CalculationClass {
    private Map<Integer, Object> IakData;
    private Map<String, Object> ParsedData;
    private Map<Integer ,Map<String, Double>> ItemsAndCosts = new HashMap<>();
    private Set<ObjectPlant> plantsFromIAK = new HashSet<>();
    private Set<ObjectLine> linesFromIAK = new HashSet<>();

    public CalculationClass(Map<Integer, Object> IakData, Map<String, Object> ParsedData){
        this.IakData = IakData;
        this.ParsedData = ParsedData;
    }

    public CalculationClass(Set<ObjectPlant> plantsFromIAK ,Set<ObjectLine> linesFromIAK, Map<String, Object> ParsedData){
        this.plantsFromIAK = plantsFromIAK;
        this.linesFromIAK = linesFromIAK;
        this.ParsedData = ParsedData;
    }

    public Map<Integer ,Map<String, Double>> StartAllCalculations(){
        List<String> KeysFromParsedData = new ArrayList<>();
        List<String> ItemsList = new ArrayList<>();
        List<Double> NaturaList = new ArrayList<>();
        List<Double> CostsList = new ArrayList<>();
        int line_count = 0;

        for (String key : ParsedData.keySet()) {
            KeysFromParsedData.add(key);
        }

        List<Plant> ParsedPlants = (List<Plant>) ParsedData.get(KeysFromParsedData.get(KeysFromParsedData.size() - 1));

        for (ObjectPlant plantFromIak : plantsFromIAK) {
            if (linesFromIAK.stream().filter(l->l.getPlant_ID()==plantFromIak.getId()).noneMatch(ObjectLine::isEnable))
                continue;
            else {
                for (Plant Plant : ParsedPlants) {
                    if (Plant.Id == plantFromIak.getId()) {
                        for (Item Item : Plant.Items) {
                            switch (Item.NaturalCalc) {
                                case NaturalAndProcent:
                                    ItemsList.add(Plant.Name + " " + Item.Name);
                                    NaturaList.add(NaturalAdProcent(Plant, Item, plantFromIak.getModelMonthNumber(), (List<NaturalAdProcent>) ParsedData.get("Натуральная показатель на процент")));
                                    break;
                                case OnlyProcent:
                                    ItemsList.add(Plant.Name + " " + Item.Name);
                                    NaturaList.add(plantFromIak.getGnsForHeat() * OnlyProcent(Plant, Item, (List<OnlyProcent>) ParsedData.get("Только процентный показатель")));
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
                                    CostsList.add(CostAdKoef(Plant, plantFromIak.getModelMonthNumber(), Item, (List<CostAdKoef>) ParsedData.get("Стоимость на коеффициент")));
                                    break;
                                case OnlyCost:
                                    CostsList.add(OnlyCost(Plant, Item, (List<OnlyCost>) ParsedData.get("Только стоимость")));
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                        }
                        for (ObjectLine objLine : linesFromIAK) {
                            for (Line Line : Plant.Lines) {
                                if (Line.Id == objLine.getId()) {
                                    for (Item Item : Line.Items) {
                                        switch (Item.NaturalCalc) {
                                            case NaturalAndProcent:
                                                ItemsList.add(Line.Description + " " + Item.Name);
                                                NaturaList.add(NaturalAdProcent(Line, objLine, Item, (List<NaturalAdProcent>) ParsedData.get("Натуральная показатель на процент")));
                                                break;
                                            case OnlyProcent:
                                                //ItemsList.add(Line.Description + " " + Item.Name);
                                                //CostsList
                                                break;
                                            case Natural:
                                                if (objLine.isEnable()) {
                                                    ItemsList.add(Line.Description + " " + Item.Name);
                                                    NaturaList.add(objLine.getGnsForHeat());
                                                }
                                                break;
                                            default:
                                                throw new AssertionError();
                                        }
                                        switch (Item.CostCalc) {
                                            case CostAndKoef:
                                                CostsList.add(CostAdKoef(Line, objLine, Item, (List<CostAdKoef>) ParsedData.get("Стоимость на коеффициент")));
                                                break;
                                            case OnlyCost:
                                                CostsList.add(OnlyCost(Line, objLine, Item, (List<OnlyCost>) ParsedData.get("Только стоимость")));
                                                break;
                                            default:
                                                throw new AssertionError();
                                        }
                                    }
                                }
                                line_count++;
                            }
                        }
                        ItemsAndCosts.put(plantFromIak.getId(), new HashMap<>());
                        Map<String, Double> tempItems = new HashMap<>();
                        for (int i = 0; i < ItemsList.size(); i++)
                            tempItems.put(ItemsList.get(i), NaturaList.get(i) * CostsList.get(i));
                        ItemsAndCosts.get(plantFromIak.getId()).putAll(tempItems);
                    }
                    line_count = 0;
                }
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

    private Double NaturalAdProcent(Plant Plant, Item Item, int monthNumber,List<NaturalAdProcent> NaturalAdProcents){
        for (NaturalAdProcent oneNatur : NaturalAdProcents) {
            if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Plant.Name + " " + monthNumber))
                return oneNatur.Natural * oneNatur.Procent;
        }
        return 0.0;
    }

    private Double NaturalAdProcent(Line Line, ObjectLine objLine, Item Item, List<NaturalAdProcent> NaturalAdProcents){
        for (NaturalAdProcent oneNatur : NaturalAdProcents) {
            if(objLine.isEnable()){
                if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(objLine.getModeId()).Name + " " + objLine.getModelMonthNumber())){
                        return oneNatur.Natural * oneNatur.Procent;
                }
            }
            else{
                if(oneNatur.Name.equals(Item.Name) && oneNatur.Description.equals(Line.Description + " " + "Ремонт" + " " + objLine.getModelMonthNumber())){
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

    private Double CostAdKoef(Plant Plant, int monthNumber, Item Item, List<CostAdKoef> CostAdKoefs){
        for (CostAdKoef cost : CostAdKoefs) {
            if(cost.Name.equals(Item.Name) && cost.Description.equals(Plant.Name + " " + monthNumber))
                return cost.Cost * cost.Koef;
        }
        return 0.0;
    }

    private Double CostAdKoef(Line Line, ObjectLine objectLine, Item Item, List<CostAdKoef> CostAdKoefs){
        for (CostAdKoef cost : CostAdKoefs) {
            if(objectLine.isEnable()){
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(objectLine.getModeId()).Name + " " + objectLine.getModelMonthNumber()))
                    return cost.Cost * cost.Koef;

            }
            else{
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Ремонт" + " " + objectLine.getModelMonthNumber()))
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

    private Double OnlyCost(Line Line, ObjectLine objLine, Item Item, List<OnlyCost> OnlyCosts){
        for (OnlyCost cost : OnlyCosts) {
            if(objLine.isEnable()){
                if(cost.Name.equals(Item.Name) && cost.Description.equals(Line.Description + " " + "Работа" + " " + Line.Modes.get(objLine.getModeId()).Name))
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
