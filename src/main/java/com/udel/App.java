package com.udel;

import java.util.Set;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataFromIAK.ClassCostFromIAK;
import com.udel.dataMiner.dataModel.enums.ConditionType;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectModel;
import com.udel.objectModel.ObjectPlant;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {

        ///Заполняем базу данными
        //DataSeaderClass Seader = new DataSeaderClass();
        //Seader.InitMethod();
        //Seader.uskSeader();

        /// Данные из базы загружаются в память (при старте модели)
        CalculationClass calculationClass = new CalculationClass();

        /// Формируем входящие данные
        ClassCostFromIAK GSNcost = new ClassCostFromIAK();
        GSNcost.setName("GSN");
        GSNcost.setCost(100.);
        ObjectPlant plant1 = new ObjectPlant(1, "УСК");
        plant1.addObjectModelParameter(1, 100.);
        ObjectLine line1 = new ObjectLine(2,"УСК Линия 1",0, ConditionType.Work);
        line1.addObjectModelParameter(1, 10.);
        ObjectLine line2 = new ObjectLine(3,"УСК Линия 2", 3, ConditionType.Work);
        line2.addObjectModelParameter(1, 10.);

        Set<ObjectModel> objectParametersSet = Set.of(plant1,line1,line2);

        int modelMonthNumber = 10;

        /// Вызываем расчёт и выводим результат в консоль
        calculationClass.setUserData(GSNcost);
        var result = calculationClass.StartAllCalculations(objectParametersSet, modelMonthNumber);
        System.out.println(calculationClass.toString(result, objectParametersSet));
    }
}
