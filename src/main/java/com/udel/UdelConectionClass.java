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
    private Map<String, Object> DataMap = new HashMap<>();
    private Map<Integer, Map<String, Double>> ItemToCosts = new HashMap<>();

    public UdelConectionClass() {}

    public void UdelCalculationProvider(Map<Integer, Object> IakData){
        CalculationClass Callculate = new CalculationClass(IakData, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        System.out.println(Callculate.toString());
    }
    public String UdelCalculationProvider(Set<ObjectPlant> plantsFromIAK, Set<ObjectLine> linesFromIAK){
        CalculationClass Callculate = new CalculationClass(plantsFromIAK,linesFromIAK, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        return (Callculate.toString());
    }
    public String UdelCalculationProvider(Set<ObjectModel> objectModels, Integer monthNumber){
        CalculationClass Callculate = new CalculationClass();
        //ItemToCosts =  Callculate.StartAllCalculations(objectModels, monthNumber);
        return (Callculate.toString());
    }
}
