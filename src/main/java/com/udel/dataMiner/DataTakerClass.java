package com.udel.dataMiner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.udel.dataMiner.dataModel.Condition;
import com.udel.dataMiner.dataModel.Item;
import com.udel.dataMiner.dataModel.Line;
import com.udel.dataMiner.dataModel.Mode;
import com.udel.dataMiner.dataModel.Plant;
import com.udel.dataMiner.dataModel.enums.PlantGroup;
import com.udel.dataMiner.dataModel.enums.TypeOfCost;
import com.udel.dataMiner.dataModel.enums.TypeOfNatural;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.CostAdKoef;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.Inflation;
import com.udel.dataMiner.dataModel.tabelsForCalc.costs.OnlyCost;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.NaturalAdProcent;
import com.udel.dataMiner.dataModel.tabelsForCalc.naturals.OnlyProcent;

public class DataTakerClass {
    private List<Plant> Plants;
    private List<Line> Lines;
    private List<Mode> Modes;
    private List<Item> Items;
    private List<NaturalAdProcent> NaturalAdProcents;
    private List<OnlyProcent> OnlyProcents;
    private List<CostAdKoef> CostAdKoefs;
    private List<OnlyCost> OnlyCosts;
    private List<Inflation> Inflations;    

    public DataTakerClass(){
        //TestDataFunc();
        TestDataBaseSeed();

    }

    private void DataTakerFromSQlite(){
        
    }

    private void TestDataBaseSeed(){
        Plant plant = new Plant();
        plant.Name = "УСК";
        plant.Description = "Установка Стабилизации Конденсата";
        plant.PlantGroup = PlantGroup.PLANT;
        
        Line line1 = new Line();
        line1.Name = "Линия 1";
        line1.Description = "УСК Линия 1";
        line1.PlantName = plant.Name;
        line1.Plant = plant;

        Line line2 = new Line();
        line2.Name = "Линия 2";
        line2.Description = "УСК Линия 2";
        line2.PlantName = plant.Name;
        line2.Plant = plant;

        Item item1 = new Item();
        item1.Name = "Технологическая Электроэнергия";
        item1.Description = "Описание";
        item1.Line = line1;
        item1.NaturalCalc = TypeOfNatural.NaturalAndProcent;
        item1.CostCalc = TypeOfCost.CostAndKoef;

        Item item2 = new Item();
        item2.Name = "Технологическая Электроэнергия";
        item2.Description = "Описание";
        item2.Line = line2;
        item2.NaturalCalc = TypeOfNatural.NaturalAndProcent;
        item2.CostCalc = TypeOfCost.CostAndKoef;

        Item item3 = new Item();
        item3.Name = "ГСН";
        item3.Description = "Описание";
        item3.Line = line1;
        item3.NaturalCalc = TypeOfNatural.Natural;
        item3.CostCalc = TypeOfCost.OnlyCost;

        Item item4 = new Item();
        item4.Name = "ГСН";
        item4.Description = "Описание";
        item4.Line = line1;
        item4.NaturalCalc = TypeOfNatural.Natural;
        item4.CostCalc = TypeOfCost.OnlyCost;

        Item item5 = new Item();
        item5.Name = "Вспомогательная Электроэнергия";
        item5.Description = "Описание";
        item5.Plant = plant;
        item5.NaturalCalc = TypeOfNatural.NaturalAndProcent;
        item5.CostCalc = TypeOfCost.CostAndKoef;

        Item item6 = new Item();
        item6.Name = "Отопление";
        item6.Description = "Описание";
        item6.Plant = plant;
        item6.NaturalCalc = TypeOfNatural.OnlyProcent;
        item6.CostCalc = TypeOfCost.OnlyCost;

        Condition condition1 = new Condition();
        condition1.Name = "Работа";
        condition1.Description = "Режим работы";
        Condition condition2 = new Condition();
        condition2.Name = "Ремонт";
        condition2.Description = "Режим ремонта";
        Condition condition3 = new Condition();
        condition3.Name = "Простой";
        condition3.Description = "Режим простоя";

        Mode mode1 = new Mode();
        mode1.Name = "ДК";
        mode1.Line = line1;
        Mode mode2 = new Mode();
        mode2.Name = "СК(ДТ)";
        mode2.Line = line1;
        Mode mode3 = new Mode();
        mode3.Name = "СК(ТС-1)";
        mode3.Line = line1;
        Mode mode4 = new Mode();
        mode4.Name = "Лег.СК";
        mode4.Line = line1;

        Mode mode5 = new Mode();
        mode5.Name = "ДК";
        mode5.Line = line2;
        Mode mode6 = new Mode();
        mode6.Name = "СК(ДТ)";
        mode6.Line = line2;
        Mode mode7 = new Mode();
        mode7.Name = "СК(ТС-1)";
        mode7.Line = line2;
        Mode mode8 = new Mode();
        mode8.Name = "Лег.СК";
        mode8.Line = line2;

        List<Mode> modes1 = new ArrayList<>();
        modes1.add(mode1);
        modes1.add(mode2);
        modes1.add(mode3);
        modes1.add(mode4);
        line1.Modes = modes1;

        List<Mode> modes2 = new ArrayList<>();
        modes2.add(mode5);
        modes2.add(mode6);
        modes2.add(mode7);
        modes2.add(mode8);
        line2.Modes = modes2;

        List<Condition> conditions = new ArrayList<>();
        conditions.add(condition1);
        conditions.add(condition2);
        conditions.add(condition3);
        line1.Conditions = conditions;
        line2.Conditions = conditions;

        List<Item> items1 = new ArrayList<>();
        items1.add(item1);
        items1.add(item2);
        line1.Items = items1;
        List<Item> items2 = new ArrayList<>();
        items2.add(item3);
        items2.add(item4);
        line2.Items = items2;
        List<Item> items3 = new ArrayList<>();
        items3.add(item5);
        items3.add(item6);
        plant.Items =items3;
        
        List<Line> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        plant.Lines = lines;

        SQLiteMiner.savePlant(plant);

        for (Condition cond : conditions)
            SQLiteMiner.saveCondition(cond);

        /*for (Line line : lines) {
            SQLiteMiner.saveLine(line);
        }

        for (Item item : items1) {
            SQLiteMiner.saveItem(item);
        }

        for (Item item : items2) {
            SQLiteMiner.saveItem(item);
        }

        for (Item item : items3) {
            SQLiteMiner.saveItem(item);
        }

        for (Mode mode : modes1) {
            SQLiteMiner.saveMode(mode);
        }

        for (Mode mode : modes2) {
            SQLiteMiner.saveMode(mode);
        }

        for (Condition cond : conditions) {
            SQLiteMiner.saveCondition(cond);
        }*/
    }

    public Map<String, Object> ParsinById(List<Integer> InitMass){
        List<Plant> temp = new ArrayList<>();
        Map<String, Object> data = new LinkedHashMap<>();
        for (int Id : InitMass) {
            for (Plant Plant : Plants) {
                if(Plant.Id == Id)
                    temp.add(Plant);
            }
        }

        data.put("Данные из базы", temp);
        data.put("Натуральная показатель на процент", NaturalAdProcents);
        data.put("Только процентный показатель", OnlyProcents);
        data.put("Стоимость на коеффициент", CostAdKoefs);
        data.put("Только стоимость", OnlyCosts);
        data.put("Инфляция", Inflations);

        return data;
    }

    /*private void TestDataFunc(){
        Plants = new ArrayList<>();
        Lines = new ArrayList<>();
        Modes = new ArrayList<>();
        Items = new ArrayList<>();

        Items.add(new Item(0, "Технологическая Электроэнергия", "Описание", TypeOfNatural.NaturalAndProcent, TypeOfCost.CostAndKoef));
        Items.add(new Item(1, "ГСН", "Описание", TypeOfNatural.Natural, TypeOfCost.OnlyCost));
        Items.add(new Item(2, "Вспомогательная Электроэнергия", "Описание", TypeOfNatural.NaturalAndProcent, TypeOfCost.CostAndKoef));
        Items.add(new Item(3, "Отопление", "Описание", TypeOfNatural.OnlyProcent, TypeOfCost.OnlyCost));

        Modes.add(new Mode(0, "ДК", 0, "УСК"));
        Modes.add(new Mode(1, "СК(ДТ)", 0, "УСК"));
        Modes.add(new Mode(2, "СК(ТС-1)", 0, "УСК"));
        Modes.add(new Mode(3, "Лег.СК", 0, "УСК"));

        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition(0, "Работа", "Режим работы"));
        conditions.add(new Condition(1, "Ремонт", "Режим ремонта"));
        conditions.add(new Condition(2, "Простой", "Режим простоя"));
        Lines.add(new Line(0, "Линия 1", "УСК Линия 1", "УСК", 0, conditions, new ArrayList<>(), new ArrayList<>()));
        Lines.add(new Line(1, "Линия 2", "УСК Линия 2", "УСК", 0, conditions, new ArrayList<>(), new ArrayList<>()));

        Plants.add(new Plant(0, "УСК", "Установка Стабилизации Конденсата", new ArrayList<>(), PlantGroup.PLANT, new ArrayList<>()));

        Lines.get(0).PlantId = Plants.get(0).Id;
        Lines.get(0).Items.add(Items.get(1));
        Lines.get(0).Items.add(Items.get(0));
        Lines.get(0).Modes.addAll(Modes);
        Lines.get(1).PlantId = Plants.get(0).Id;
        Lines.get(1).Items.add(Items.get(1));
        Lines.get(1).Items.add(Items.get(0));
        Lines.get(1).Modes.addAll(Modes);

        Plants.get(0).Lines.add(Lines.get(0));
        Plants.get(0).Lines.add(Lines.get(1));
        Plants.get(0).Items.add(Items.get(2));
        Plants.get(0).Items.add(Items.get(3));

        TestSeedData(Plants);
    }

    private void TestSeedData(List<Plant> Plants){
        double[] _Palnt_Elec_Proc = {0.98, 0.8, 0.89, 0.96, 0.72, 0.87, 0.86, 0.92, 0.89, 1, 0.71, 0.78};
        double[] _Plant_Elec_Koef = {0.95, 0.98, 0.97, 0.97, 0.97, 0.98, 1, 1.02, 1.07, 1.03, 1.03, 1.03};
        double[] _Plant_Elec_Rej_Proc= {0.58, 0.58, 1, 1, 1, 1, 1, 1, 1, 1, 0.58, 0.58};
        double _Plant_Elec_Cost = 6.21;
        double _Plant_Elec_Natural = 65.76;
        double _Plant_Elec_Rej_Natural = 257;

        NaturalAdProcents = new ArrayList<>();
        OnlyProcents = new ArrayList<>();
        CostAdKoefs = new ArrayList<>();
        OnlyCosts = new ArrayList<>();
        Inflations = new ArrayList<>();
        int temp;
        for (Plant Plant : Plants) {
            for (Item Item : Plant.Items) {
                switch (Item.NaturalCalc) {
                    case NaturalAndProcent:
                        temp = NaturalAdProcents.size();
                        for (int i = 0; i < _Palnt_Elec_Proc.length; i++) {
                            NaturalAdProcents.add(new NaturalAdProcent(temp, Item.Name, Plant.Name + " " + i , _Palnt_Elec_Proc[i], _Plant_Elec_Natural));
                            temp++;
                        }
                        break;
                    case OnlyProcent:
                        temp = OnlyProcents.size();
                        OnlyProcents.add(new OnlyProcent(temp, Item.Name, Plant.Name, 0.05));
                        break;
                    case Natural:
                        break;
                    default:
                        throw new AssertionError();
                }
                switch (Item.CostCalc) {
                    case CostAndKoef:
                        temp = CostAdKoefs.size();
                        for (int i = 0; i < _Plant_Elec_Koef.length; i++) {
                            CostAdKoefs.add(new CostAdKoef(i, Item.Name, Plant.Name + " " + i, _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                            temp++;
                        }
                        break;
                    case OnlyCost:
                        temp = OnlyCosts.size();
                        OnlyCosts.add(new OnlyCost(temp, Item.Name, Plant.Name, 500));
                        break;
                    default:
                        throw new AssertionError();
                }
            }

            for (Line Line : Plant.Lines) {
                for (Condition Cond : Line.Conditions) {
                    if("Работа".equals(Cond.Name)){
                        for (Mode Mode : Line.Modes) {
                            for (Item Item : Line.Items) {
                                switch (Item.NaturalCalc) {
                                    case NaturalAndProcent:
                                        temp = NaturalAdProcents.size();
                                            for (int i = 0; i < _Plant_Elec_Rej_Proc.length; i++) {
                                                NaturalAdProcents.add(new NaturalAdProcent(temp, Item.Name,  Line.Description + " " + Cond.Name + " " + Mode.Name + " " + i , _Plant_Elec_Rej_Proc[i], _Plant_Elec_Rej_Natural));
                                                temp++;
                                            }
                                        break;
                                    case OnlyProcent:
                                        temp = OnlyProcents.size();
                                        OnlyProcents.add(new OnlyProcent(temp, Item.Name, Plant.Name, 0.05));
                                        break;
                                    case Natural:
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                switch (Item.CostCalc) {
                                    case CostAndKoef:
                                        temp = CostAdKoefs.size();
                                        for (int i = 0; i < _Plant_Elec_Koef.length; i++) {
                                            CostAdKoefs.add(new CostAdKoef(i, Item.Name, Line.Description + " " + Cond.Name + " " + Mode.Name + " " + i, _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                                            temp++;
                                        }
                                        break;
                                    case OnlyCost:
                                        temp = OnlyCosts.size();
                                        OnlyCosts.add(new OnlyCost(temp, Item.Name, Line.Description + " " + Cond.Name + " " + Mode.Name, 500));
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                            }
                        }
                    }
                    else if ("Простой".equals(Cond.Name) || "Ремонт".equals(Cond.Name)) {
                        for (Item Item : Line.Items) {
                            switch (Item.NaturalCalc) {
                                    case NaturalAndProcent:
                                        temp = NaturalAdProcents.size();
                                            for (int i = 0; i < _Plant_Elec_Rej_Proc.length; i++) {
                                                NaturalAdProcents.add(new NaturalAdProcent(temp, Item.Name,  Line.Description + " " + Cond.Name + " " + i , 1, 0));
                                                temp++;
                                            }
                                        break;
                                    case OnlyProcent:
                                        temp = OnlyProcents.size();
                                        OnlyProcents.add(new OnlyProcent(temp, Item.Name, Plant.Name, 0.05));
                                        break;
                                    case Natural:
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                switch (Item.CostCalc) {
                                    case CostAndKoef:
                                        temp = CostAdKoefs.size();
                                        for (int i = 0; i < _Plant_Elec_Koef.length; i++) {
                                            CostAdKoefs.add(new CostAdKoef(i, Item.Name, Line.Description + " " + Cond.Name + " " + i, _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                                            temp++;
                                        }
                                        break;
                                    case OnlyCost:
                                        temp = OnlyCosts.size();
                                        OnlyCosts.add(new OnlyCost(temp, Item.Name, Line.Description + " " + Cond.Name, 500));
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            Inflations.add(new Inflation(i, "Инфляция", i + "", 1.0 + i * 0.03));
        }
    }*/
}
