package com.udel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataMiner.DataTakerClass;
import com.udel.objectModel.ObjectLine;
import com.udel.objectModel.ObjectPlant;

public class UdelConectionClass {
    private DataTakerClass AllData = new DataTakerClass();
    private List<Integer> InitMass = new ArrayList<>();
    private Map<String, Object> DataMap = new HashMap<>();
    private Map<Integer, Map<String, Double>> ItemToCosts = new HashMap<>();

    public UdelConectionClass() {}

    public void UdelItitial(List<Integer> InitMass, Set<Plant> plantsFromIAK){
        this.InitMass = InitMass;
        this.DataMap = AllData.ParsinById(this.InitMass);

        this.plantsFromIAK = plantsFromIAK;
    }

    public void UdelCalculationProvider(Map<Integer, Object> IakData, Set<Line> linesFromIAK){
        this.linesFromIAK = linesFromIAK;
        CalculationClass Callculate = new CalculationClass(IakData, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        System.out.println(Callculate.toString());
    }
    public String UdelCalculationProvider(Set<ObjectPlant> plantsFromIAK, Set<ObjectLine> linesFromIAK){
        CalculationClass Callculate = new CalculationClass(plantsFromIAK,linesFromIAK, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        return (Callculate.toString());
    }
}
