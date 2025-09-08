package com.udel.calcMiner;

import java.util.*;

import com.udel.dataMiner.DataTakerClass;
import com.udel.dataMiner.dataModel.*;
import com.udel.dataMiner.dataModel.enums.CalculationType;
import com.udel.dataMiner.dataModel.enums.ConditionType;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectModel;
import com.udel.objectModel.ObjectPlant;

public class CalculationClass {

    private DataTakerClass AllData = new DataTakerClass();

    public CalculationClass(){
    }

    public Map<Integer, Map<String, Double>> StartAllCalculations(Set<ObjectModel> objectModels, Integer monthNumber) throws Exception {

        Map<Integer, Map<String, Double>> itemsAndCosts = new HashMap<>();

        for (ObjectModel objectModel : objectModels)
        {
            ObjectEntity objectEntity = AllData.Objects.stream()
                .filter(o->o.Id==objectModel.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нет объекта в БД с id " + objectModel.getId()));

            Map<String, Double> itemCosts = new HashMap<>();
            for (ItemsInObject itemsInObject : objectEntity.getItemsInObjects())
            {
                double cost = switch (itemsInObject.getCalculationMethod().getType()) {
                    case NaturalProcentCostKoef -> calculateNaturalProcentCostKoef(objectModel, objectEntity, itemsInObject, monthNumber);
                    case ParameterProcentCost   -> calculateParameterProcentCost(objectModel, objectEntity, itemsInObject);
                    case ParameterCost          -> calculateParameterCost(objectModel, objectEntity, itemsInObject, monthNumber);
                };
                itemCosts.put(itemsInObject.getItem().Name, cost);
            }
            itemsAndCosts.put(objectModel.getId(),itemCosts);
        }
        return itemsAndCosts;
    }

    private double calculateNaturalProcentCostKoef(
        ObjectModel objectModel,
        ObjectEntity objectEntity,
        ItemsInObject itemsInObject,
        int monthNumber
    ) {
        String description = buildDescription(objectModel, objectEntity, monthNumber, CalculationType.NaturalProcentCostKoef);
        System.out.println(description);
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, description);

        return params.Natural * params.Procent * params.Cost * params.Koef;
    }
    private double calculateParameterProcentCost(
        ObjectModel objectModel,
        ObjectEntity objectEntity,
        ItemsInObject itemsInObject
    ) {
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, objectEntity.Name);

        double parameter = 0.0;
        ModelParameters param1 = itemsInObject.getParameter1();
        if (param1 != null) {
            parameter = objectModel.getObjectModelParameters().getOrDefault(param1.Id, 0.0);
        }

        return parameter * params.Procent * params.Cost;
    }

    private double calculateParameterCost(
        ObjectModel objectModel,
        ObjectEntity objectEntity,
        ItemsInObject itemsInObject,
        int monthNumber
    ) {
        String description = buildDescription(objectModel, objectEntity, monthNumber, CalculationType.ParameterCost);
        System.out.println(description);

        ObjectParameters params = findParameters(itemsInObject.getItem().Name, description);

        double parameter = 0.0;
        ModelParameters param = itemsInObject.getParameter1();
        if (param != null) {
            parameter = objectModel.getObjectModelParameters().getOrDefault(param.Id, 0.0);
        }

        return parameter * params.Cost;
    }

    public String buildDescription(ObjectModel objectModel, ObjectEntity objectEntity, Integer monthNumber, CalculationType calculationType)
    {
        switch (calculationType){
            case NaturalProcentCostKoef:
                if (objectModel instanceof ObjectPlant plant)
                {
                    return (plant.getName() + " " + monthNumber);
                }
                else if (objectModel instanceof ObjectLine line)
                {
                    if (line.getConditionType() == ConditionType.Work)
                        return (line.getName() + " " + line.getConditionType() + " " + objectEntity.getModesInObjects().get(line.getModeId()).Name + " " + monthNumber);
                    else
                        return (line.getName() + " " + line.getConditionType() + " " + monthNumber);
                }
                break;
            case ParameterCost:
                if (objectModel instanceof ObjectLine line)
                {
                    if (line.getConditionType() == ConditionType.Work)
                        return (line.getName() + " " + line.getConditionType() + " " + objectEntity.getModesInObjects().get(line.getModeId()).Name);
                    else
                        return (line.getName() + " " + line.getConditionType());
                }
                break;
        }
        return "";
    }

    private ObjectParameters findParameters(String name, String description) {
        return AllData.ObjectParameters.stream()
            .filter(o -> Objects.equals(o.Name, name) && Objects.equals(o.Description, description))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Не найдены параметры для " + name + " - " + description));
    }

    public String toString(Map<Integer, Map<String, Double>> itemsAndCosts) {
        StringBuilder sb = new StringBuilder();
        for (var entry : itemsAndCosts.entrySet()) {
            sb.append("id - ").append(entry.getKey()).append("\n");
            entry.getValue().forEach((item, cost) ->
                sb.append("\t").append(item).append(" - ").append(cost).append("\n")
            );
        }
        return sb.toString();
    }
}
