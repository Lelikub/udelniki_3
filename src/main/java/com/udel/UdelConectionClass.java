package com.udel;

import java.util.*;

import com.udel.calcMiner.CalculationClass;
import com.udel.dataMiner.DataTakerClass;
import com.udel.objectModel.Line;
import com.udel.objectModel.Plant;

public class UdelConectionClass {
    private DataTakerClass AllData = new DataTakerClass();
    private List<Integer> InitMass = new ArrayList<>();
    private Map<String, Object> DataMap = new HashMap<>();
    private Map<Integer, Map<String, Double>> ItemToCosts = new HashMap<>();

    /**
     * На вход модуля будут поступать два Set с наборами установок и линий
     */
    private Set<Plant> plantsFromIAK = new HashSet<>();
    private Set<Line> linesFromIAK = new HashSet<>();

    public UdelConectionClass() {}

    public void UdelItitial(List<Integer> InitMass){
        this.InitMass = InitMass;
        this.DataMap = AllData.ParsinById(this.InitMass);
    }

    public void UdelCalculationProvider(Map<Integer, Object> IakData){
        CalculationClass Callculate = new CalculationClass(IakData, DataMap);
        ItemToCosts =  Callculate.StartAllCalculations();
        System.out.println(Callculate.toString());
    }
}
