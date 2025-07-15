package com.udel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.udel.dataFromIAK.CostParameter;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        List<Integer> TestInitList = new ArrayList<>();
        TestInitList.add(1);

        Map<Integer, Object> TestParams = new HashMap<>();
        List<CostParameter> UskParams = new ArrayList<>();
        UskParams.add(new CostParameter(1));
        UskParams.add(new CostParameter(2));

        Map<Integer, Double> EnterFlows = new HashMap<>();
        Map<Integer, Double> ExitFlows = new HashMap<>();

        EnterFlows.put(0, 10.0);
        ExitFlows.put(0, 1000.0);

        UskParams.get(1).enter.setEnterFlows(EnterFlows);
        UskParams.get(2).enter.setEnterFlows(EnterFlows);

        UskParams.get(1).general.setIsCostCenterActive(true);
        UskParams.get(1).general.setCostCenterState(2);
        UskParams.get(1).general.setModelMonthNumber(10);
        UskParams.get(1).general.setLaodingLevel(0.9);
        UskParams.get(1).general.setGnsForHeat(100);
        UskParams.get(2).general.setIsCostCenterActive(true);
        UskParams.get(2).general.setCostCenterState(2);
        UskParams.get(2).general.setModelMonthNumber(10);
        UskParams.get(2).general.setLaodingLevel(0.9);
        UskParams.get(2).general.setGnsForHeat(100);

        UskParams.get(1).exit.setexitFlows(ExitFlows);
        UskParams.get(2).exit.setexitFlows(ExitFlows);

        for (CostParameter Param : UskParams) {
            System.out.println(Param.toString());
        }
        
        TestParams.put(1, UskParams);

        UdelConectionClass test = new UdelConectionClass();

        //test.UdelItitial(TestInitList);

        //test.UdelCalculationProvider(TestParams);
    }
}
