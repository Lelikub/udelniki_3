package com.udel;

import java.util.*;

import com.udel.dataMiner.dataModel.enums.ConditionType;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectModel;
import com.udel.objectModel.ObjectPlant;

public final class App {
    private App() {
    }

    public static void main(String[] args) {

        /// Данные из базы загружаются в память (при старте модели)
        UdelConectionClass udelConnection = new UdelConectionClass();

        /// Формируем входящие данные
        ObjectPlant plant1 = new ObjectPlant(1, "УСК");
        plant1.addObjectModelParameter(1, 15.5);
        ObjectLine line1 = new ObjectLine(2,"Линия 1",1, ConditionType.Work);
        plant1.addObjectModelParameter(1, 10.5);
        ObjectLine line2 = new ObjectLine(3,"Линия 2", 2, ConditionType.Work);
        plant1.addObjectModelParameter(1, 12.5);

        Set<ObjectModel> objectParametersSet = Set.of(plant1,line1,line2);

        int modelMonthNumber = 0;

        udelConnection.UdelCalculationProvider(objectParametersSet, modelMonthNumber);
    }
}
