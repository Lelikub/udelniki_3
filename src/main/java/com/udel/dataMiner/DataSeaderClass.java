package com.udel.dataMiner;
import java.util.ArrayList;
import java.util.List;

import com.udel.dataMiner.dataModel.CalculationMethod;
import com.udel.dataMiner.dataModel.Item;
import com.udel.dataMiner.dataModel.ItemsInObject;
import com.udel.dataMiner.dataModel.Mode;
import com.udel.dataMiner.dataModel.ModelParameters;
import com.udel.dataMiner.dataModel.ObjectEntity;
import com.udel.dataMiner.dataModel.ObjectParameters;
import static com.udel.dataMiner.dataModel.enums.CalculationType.NaturalProcentCostKoef;
import static com.udel.dataMiner.dataModel.enums.CalculationType.ParameterCost;
import static com.udel.dataMiner.dataModel.enums.CalculationType.ParameterProcentCost;
import com.udel.dataMiner.dataModel.enums.ConditionType;
import com.udel.dataMiner.dataModel.enums.ObjectTypes;
import com.udel.dataMiner.dataModel.tablesForCalc.costs.Inflation;

public class DataSeaderClass {

    CalculationMethod method1;
    CalculationMethod method2;
    CalculationMethod method3;
    ModelParameters mparam1;
    ModelParameters mparam2;
    ModelParameters mparam3;
    Item item1;
    Item item2;
    Item item3;
    Item item4;


    public DataSeaderClass() {
        this.method1 = new CalculationMethod(NaturalProcentCostKoef);
        this.method2 = new CalculationMethod(ParameterProcentCost);
        this.method3 = new CalculationMethod(ParameterCost);
        this.mparam1 = new ModelParameters("ГСН на отопление");
        this.mparam2 = new ModelParameters("Уровень загрузки");
        this.mparam3 = new ModelParameters("Входящий поток");
        this.item1 = new Item("Технологическая Электроэнергия");
        this.item2 = new Item("ГСН");
        this.item3 = new Item("Вспомогательная Электроэнергия");
        this.item4 = new Item("Отопление");
    }

    public void InitMethod(){
        SQLiteMiner.saveEntities(List.of(method1, method2, method3));

        SQLiteMiner.saveEntities(List.of(mparam1, mparam2, mparam3));

        SQLiteMiner.saveEntities(List.of(item1, item2, item3, item4));
    }

    public void uskSeader(){
        ObjectEntity ysk = new ObjectEntity("УСК","Установка Стабилизации Конденсата",ObjectTypes.PLANT);
        
        ObjectEntity line1 = new ObjectEntity("Линия 1","УСК Линия 1",ObjectTypes.LINE, ysk);
        ObjectEntity line2 = new ObjectEntity("Линия 2","УСК Линия 2",ObjectTypes.LINE, ysk);

        ItemsInObject itemsInObject1 = new ItemsInObject(ysk,item1,method1);
        ItemsInObject itemsInObject2 = new ItemsInObject(ysk,item2,method2,mparam1);
        ysk.setItemsInObjects(List.of(itemsInObject1,itemsInObject2));

        ItemsInObject itemsInObject3 = new ItemsInObject(line1,item3,method1);
        ItemsInObject itemsInObject4 = new ItemsInObject(line1,item4,method3,mparam1);
        line1.setItemsInObjects(List.of(itemsInObject3,itemsInObject4));

        ItemsInObject itemsInObject5 = new ItemsInObject(line2,item3,method1);
        ItemsInObject itemsInObject6 = new ItemsInObject(line2,item4,method3,mparam1);
        line2.setItemsInObjects(List.of(itemsInObject5,itemsInObject6));

        Mode mode1 = new Mode("ДК",line1);
        Mode mode2 = new Mode("СК(ДТ)",line1);
        Mode mode3 = new Mode("СК(ТС-1)",line1);
        Mode mode4 = new Mode("Лег.СК",line1);

        Mode mode5 = new Mode("ДК",line2);
        Mode mode6 = new Mode("СК(ДТ)",line2);
        Mode mode7 = new Mode("СК(ТС-1)",line2);
        Mode mode8 = new Mode("Лег.СК",line2);

        List<Mode> modes1 = new ArrayList<>();
        modes1.add(mode1);
        modes1.add(mode2);
        modes1.add(mode3);
        modes1.add(mode4);
        line1.addModes(modes1);

        List<Mode> modes2 = new ArrayList<>();
        modes2.add(mode5);
        modes2.add(mode6);
        modes2.add(mode7);
        modes2.add(mode8);
        line2.addModes(modes2);

        SQLiteMiner.saveObjects(List.of(ysk, line1,line2));
        uskCalcSeedData(List.of(ysk, line1, line2));
    }
    
     private void uskCalcSeedData(List<ObjectEntity> Objects){
        double[] _Palnt_Elec_Proc = {1.040, 1.040, 1.152, 1.040, 1.075, 0.799, 0.825, 0.799, 1.040, 1.075, 1.040, 1.075};
        double[] _Plant_Elec_Koef = {0.95, 0.98, 0.97, 0.97, 0.97, 0.98, 1, 1.02, 1.06, 1.03, 1.03, 1.03};
        double[] _Plant_Elec_Rej_Proc= {0.58, 0.58, 1, 1, 1, 1, 1, 1, 1, 1, 0.58, 0.58};
        double _Plant_Elec_Cost = 6.21;
        double _Plant_Elec_Natural = 66.42;
        double _Plant_Elec_DK_Natural = 40;
        double _Plant_Elec_SK_Natural = 257;

        List<ObjectParameters> objectParameters = new ArrayList<>();
        List<Inflation> inflations = new ArrayList<>();

        for (ObjectEntity object : Objects) {
            for (ItemsInObject itemInObject : object.getItemsInObjects()) {
                switch (itemInObject.getCalculationMethod().getType()) {
                    case NaturalProcentCostKoef:
                        for (int i = 0; i < _Palnt_Elec_Proc.length; i++) {
                            if (object.ObjectType == ObjectTypes.PLANT)
                                objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Name + " " + i, _Plant_Elec_Natural, _Palnt_Elec_Proc[i], _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                            else {
                                for (ConditionType Cond : ConditionType.values()) {
                                    if ("Работа".equals(Cond.getDisplayName())) {
                                        for (Mode Mode : object.getModesInObjects()) {
                                            if(Mode.Name.equals("ДК"))
                                                objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Description + " " + Cond.getDisplayName() + " " + Mode.Name + " " + i, _Plant_Elec_DK_Natural, _Plant_Elec_Rej_Proc[i], _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                                            else
                                                objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Description + " " + Cond.getDisplayName() + " " + Mode.Name + " " + i, _Plant_Elec_SK_Natural, _Plant_Elec_Rej_Proc[i], _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                                        }

                                    } else if ("Простой".equals(Cond.getDisplayName()) || "Ремонт".equals(Cond.getDisplayName())) {
                                        objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Description + " " + Cond.getDisplayName() + " " + i, 1, 0, _Plant_Elec_Koef[i], _Plant_Elec_Cost));
                                    }
                                }
                            }
                        }
                        break;
                    case ParameterProcentCost:
                            if (object.ObjectType == ObjectTypes.PLANT)
                                objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Name, 0.05, 500));
                        break;
                    case ParameterCost:
                        if (object.ObjectType == ObjectTypes.PLANT)
                            objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Name,500));
                        else {
                            for (ConditionType Cond : ConditionType.values()) {
                                if ("Работа".equals(Cond.getDisplayName())) {
                                    for (Mode Mode : object.getModesInObjects()) {
                                        objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Description + " " + Cond.getDisplayName() + " " + Mode.Name, 500));
                                    }

                                } else if ("Простой".equals(Cond.getDisplayName()) || "Ремонт".equals(Cond.getDisplayName())) {
                                    objectParameters.add(new ObjectParameters(0, itemInObject.getItem().Name, object.Description + " " + Cond.getDisplayName(), 500));
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i < 12; i++) {
            inflations.add(new Inflation(0, "Инфляция", i + "", 1.0 + i * 0.03));
        }

        SQLiteMiner.saveObjectParameters(objectParameters);
        SQLiteMiner.saveInflation(inflations);
    }
}
