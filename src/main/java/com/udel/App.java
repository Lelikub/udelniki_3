package com.udel;

import java.util.Map;
import java.util.Set;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataFromIAK.ClassCostFromIAK;
import com.udel.dataMiner.DataSeaderClass;
import com.udel.dataMiner.dataModel.enums.ConditionType;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectModel;
import com.udel.objectModel.ObjectPlant;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {

        ///Заполняем базу данными
        DataSeaderClass Seader = new DataSeaderClass();
        Seader.InitMethod();
        Seader.uskSeader();

        /// Данные из базы загружаются в память (при старте модели)
        CalculationClass calculationClass = new CalculationClass();

        /// Формируем входящие данные
        ClassCostFromIAK GSNcost = new ClassCostFromIAK();
        GSNcost.setName("GSN");
        GSNcost.setCost(0.495);
        ObjectPlant plant1 = new ObjectPlant(1, "УСК");
        plant1.addObjectModelParameter(1, 100.);
        ObjectLine line1 = new ObjectLine(2,"УСК Линия 1",1, ConditionType.Work);
        line1.addObjectModelParameter(1, 10.);
        ObjectLine line2 = new ObjectLine(3,"УСК Линия 2", 1, ConditionType.Work);
        line2.addObjectModelParameter(1, 10.);

        Set<ObjectModel> objectParametersSet = Set.of(plant1,line1,line2);

        int modelMonthNumber = 0;

        double[] line1_gsn = {471.2287526, 453.5682404, 539.8863831, 442.2893039, 237.7213916, 467.8228019, 496.8919146, 369.7448513, 434.9068511, 483.7327771, 476.4461953, 513.3024542};
        double[] line2_gsn = {489.9290753, 467.9220263, 548.9861928, 468.2716781, 491.3255015, 356.4150906, 351.4894971, 441.9417366, 469.6211815, 498.2846556, 472.5000027, 503.6811235};
        double[] plant_gsn = {655.2156017, 628.2924003, 742.6767142, 620.4513005, 489.087908, 566.0827856, 280.1522277, 269.2412307, 299.7761564, 669.585728, 647.6303313, 694.2419202};
        /// Вызываем расчёт и выводим результат в консоль
        calculationClass.setUserData(GSNcost);
        Map<Integer, Map<String, Double>> result;

        for (int i = 0; i < 12; i++) {
            System.out.println(i + "\n");
            plant1 = new ObjectPlant(1, "УСК");
            plant1.addObjectModelParameter(1, plant_gsn[i]);
            line1 = new ObjectLine(2,"УСК Линия 1",1, ConditionType.Work);
            line1.addObjectModelParameter(1, line1_gsn[i]);
            line2 = new ObjectLine(3,"УСК Линия 2", 1, ConditionType.Work);
            line2.addObjectModelParameter(1, line2_gsn[i]);
            objectParametersSet = Set.of(plant1,line1,line2);
            result = calculationClass.StartAllCalculations(objectParametersSet, i);
            System.out.println(calculationClass.toString(result, objectParametersSet));
        }
    }
}
