package com.udel;

import java.util.*;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataMiner.DataTakerClass;
import com.udel.dataMiner.dataModel.ObjectEntity;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectModel;
import com.udel.objectModel.ObjectPlant;

public class UdelConectionClass {
    private DataTakerClass AllData = new DataTakerClass();
    private Map<Integer, Map<String, Double>> ItemToCosts = new HashMap<>();

    public UdelConectionClass() {}

//    public String UdelCalculationProvider(Set<ObjectModel> objectModels, Integer monthNumber){
//        //CalculationClass Callculate = new CalculationClass(AllData);
//        //ItemToCosts =  Callculate.StartAllCalculations(objectModels, monthNumber);
//        //return (Callculate.toString());
//    }
}
