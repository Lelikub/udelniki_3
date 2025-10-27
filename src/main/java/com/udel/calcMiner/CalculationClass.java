package com.udel.calcMiner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.udel.dataMiner.DataTakerClass;
import com.udel.dataMiner.dataModel.ItemsInObject;
import com.udel.dataMiner.dataModel.ObjectEntity;
import com.udel.dataMiner.dataModel.ObjectParameters;
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
            /// Получаем объект из базы данных который совпадает по Id
            ObjectEntity objectEntity = AllData.Objects.stream()
                .filter(o->o.Id==objectModel.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нет объекта в БД с id " + objectModel.getId()));

            Map<String, Double> itemCosts = new HashMap<>();
            /// Считаем все статьи которые есть у объекта
            for (ItemsInObject itemsInObject : objectEntity.getItemsInObjects())
            {
                /// Выбираем метод расчета статьи
                double cost = switch (itemsInObject.getCalculationMethod().getType()) {
                    case NaturalProcentCostKoef -> calculateNaturalProcentCostKoef(objectModel, objectEntity, itemsInObject, monthNumber);
                    case ParameterProcentCost   -> calculateParameterProcentCost(objectModel, objectEntity, itemsInObject);
                    case ParameterCost          -> calculateParameterCost(objectModel, objectEntity, itemsInObject, monthNumber);
                    case JustCost               -> calculateJustCost(objectModel, objectEntity, itemsInObject, monthNumber);
                };
                itemCosts.put(itemsInObject.getItem().Name, cost);
            }
            itemsAndCosts.put(objectModel.getId(),itemCosts);
        }
        return itemsAndCosts;
    }

    ///  Первый метод расчёта
    private double calculateNaturalProcentCostKoef(ObjectModel objectModel, ObjectEntity objectEntity,
                                                   ItemsInObject itemsInObject, int monthNumber)
    {
        String description = buildDescription(objectModel, objectEntity, monthNumber, CalculationType.NaturalProcentCostKoef);
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, description);

        return params.Natural * params.Procent * params.Cost * params.Koef;
    }

    ///  Второй метод расчёта
    private double calculateParameterProcentCost(ObjectModel objectModel, ObjectEntity objectEntity, ItemsInObject itemsInObject)
    {
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, objectEntity.Name);

        double parameter = Optional.ofNullable(itemsInObject.getParameter1())
            .map(p->objectModel.getObjectModelParameter(p.Id)).orElse(0.0);

        return parameter * params.Procent * params.Cost;
    }

    ///  Третий метод расчёта
    private double calculateParameterCost(ObjectModel objectModel, ObjectEntity objectEntity, ItemsInObject itemsInObject, int monthNumber)
    {
        String description = buildDescription(objectModel, objectEntity, monthNumber, CalculationType.ParameterCost);
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, description);

        double parameter = Optional.ofNullable(itemsInObject.getParameter1())
            .map(p->objectModel.getObjectModelParameter(p.Id)).orElse(0.0);

        return parameter * params.Cost;
    }

    /// Четвертый метод расчета
    private double calculateJustCost(ObjectModel objectModel, ObjectEntity objectEntity, ItemsInObject itemsInObject, int monthNumber)
    {
        String description = buildDescription(objectModel, objectEntity, monthNumber, CalculationType.JustCost);
        ObjectParameters params = findParameters(itemsInObject.getItem().Name, description);

        return 1 * params.Cost;
    }

    /// Строим описание для дальнейшего поиска параметров в бд
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
            case JustCost:
                if (objectModel instanceof ObjectPlant plant)
                {
                    return plant.getName();
                }
        }
        return "";
    }

    /// Находим нужные параметры из базы данных для расчёта статьи
    private ObjectParameters findParameters(String name, String description) {
        return AllData.ObjectParameters.stream()
            .filter(o -> Objects.equals(o.Name, name) && Objects.equals(o.Description, description))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Не найдены параметры для " + name + " - " + description));
    }

    public String toString(Map<Integer, Map<String, Double>> itemsAndCosts, Set<ObjectModel> objectModels) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer,String> objectNames = new HashMap<>();
        for (var object : objectModels)
        {
            objectNames.put(object.getId(),object.getName());
        }

        for (var entry : itemsAndCosts.entrySet()) {
            sb.append(objectNames.get(entry.getKey())).append(", ");
            sb.append("id - ").append(entry.getKey()).append("\n");
            entry.getValue().forEach((item, cost) ->
                sb.append("\t").append(item).append(" - ").append(cost).append("\n")
            );
        }
        return sb.toString();
    }
}
