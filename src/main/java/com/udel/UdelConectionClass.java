package com.udel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataMiner.DataTakerClass;

public class UdelConectionClass {
    private DataTakerClass AllData = new DataTakerClass();
    private List<Integer> InitMass = new ArrayList<>();
    private Map<String, Object> DataMap = new HashMap<>();
    private Map<String, Double> ItemToCosts = new HashMap<>();

    public UdelConectionClass() {}

    public void UdelItitialClass(List<Integer> InitMass){
        this.InitMass = InitMass;
        this.DataMap = AllData.ParsinById(this.InitMass);
    }

    public void UdelCalculationProvider(Map<Integer, Object> IakData){
        CalculationClass Callculate = new CalculationClass(IakData, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        System.out.println(Callculate.toString());
    }
}
