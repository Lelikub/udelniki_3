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
        TestInitList.add(0);

        Map<Integer, Object> TestParams = new HashMap<>();
        List<CostParameter> UskParams = new ArrayList<>();
        UskParams.add(new CostParameter(0));
        UskParams.add(new CostParameter(1));

        Map<Integer, Double> EnterFlows = new HashMap<>();
        Map<Integer, Double> ExitFlows = new HashMap<>();

        EnterFlows.put(0, 10.0);
        ExitFlows.put(0, 1000.0);

        UskParams.get(0).enter.setEnterFlows(EnterFlows);
        UskParams.get(1).enter.setEnterFlows(EnterFlows);

        UskParams.get(0).general.setIsCostCenterActive(true);
        UskParams.get(0).general.setCostCenterState(2);
        UskParams.get(0).general.setModelMonthNumber(10);
        UskParams.get(0).general.setLaodingLevel(0.9);
        UskParams.get(0).general.setGnsForHeat(100);
        UskParams.get(1).general.setIsCostCenterActive(true);
        UskParams.get(1).general.setCostCenterState(2);
        UskParams.get(1).general.setModelMonthNumber(10);
        UskParams.get(1).general.setLaodingLevel(0.9);
        UskParams.get(1).general.setGnsForHeat(100);

        UskParams.get(0).exit.setexitFlows(ExitFlows);
        UskParams.get(1).exit.setexitFlows(ExitFlows);

        for (CostParameter Param : UskParams) {
            System.out.println(Param.toString());
        }
        
        TestParams.put(0, UskParams);

        UdelConectionClass test = new UdelConectionClass();

        test.UdelItitialClass(TestInitList);

        test.UdelCalculationProvider(TestParams);
    }
}
