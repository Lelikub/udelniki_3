package com.udel.dataFromIAK;

import java.util.HashMap;
import java.util.Map;

public class CostParameter {


    public final Enter enter;
    public final General general;
    public final Exit exit;


    public CostParameter(int costCenterId) {
        enter = new Enter();
        general = new General(costCenterId);
        exit = new Exit();
    }

    @Override
    public String toString() {
        return "CostParameter{" +
                "enter=" + enter +
                ", general=" + general +
                ", exit=" + exit +
                '}';
    }


    //Входящие параметры МВЗ
    public class Enter {
        private Map<Integer, Double> enterFlows = new HashMap<>();

        public void setEnterFlows(Map<Integer, Double> enterFlows) {
            this.enterFlows = new HashMap<>(enterFlows);
        }

        public Map<Integer, Double> getEnterFlows() {
            return this.enterFlows;
        }


        @Override
        public String toString() {
            return "Enter{" +
                    "enterFlows=" + enterFlows +
                    '}';
        }
    }

    //Прочие параметры МВЗ
    public class General {
        private int costCenterId;
        private boolean costCenterisActive;
        private int activeStateId;
        private int modelMonthNumber;
        private double laodingLevel;
        private double GnsForHeat;

        public General(int costCenterId) {
            this.costCenterId = costCenterId;
        }

        public double getGnsForHeat(){
            return this.GnsForHeat;
        }

        public void setGnsForHeat(double gnsForHeat){
            this.GnsForHeat = gnsForHeat;
        }

        public int getCostCenterId() {
            return general.costCenterId;
        }


        public void setLaodingLevel(double laodingLevel) {
            this.laodingLevel = laodingLevel;
        }

        public double getLaodingLevel() {
            return  laodingLevel;
        }

        public void setCostCenterState(int activeStateId) {
            this.activeStateId = activeStateId;
        }

        public int getCostCenterState() {
           return  this.activeStateId;
        }

        public void setIsCostCenterActive(boolean costCenterisActive) {
            this.costCenterisActive = costCenterisActive;
        }

        public boolean getIsCostCenterActive() {
            return  this.costCenterisActive;
        }


        public void setModelMonthNumber(int modelMonthNumber) {
            this.modelMonthNumber = modelMonthNumber;
        }

        public int getModelMonthNumber() {
            return  modelMonthNumber;
        }

        @Override
        public String toString() {
            return "General{" +
                    "costCenterId=" + costCenterId +
                    ", costCenterisActive=" + costCenterisActive +
                    ", activeStateId=" + activeStateId +
                    ", modelMonthNumber=" + modelMonthNumber +
                    ", laodingLevel=" + laodingLevel +
                    '}';
        }
    }

    //Выходящие параметры МВЗ
    public class Exit {
        private Map<Integer, Double> exitFlows = new HashMap<>();

        public void setexitFlows(Map<Integer, Double> exitFlows) {
            this.exitFlows = new HashMap<>(exitFlows);
        }

        public Map<Integer, Double> getExitFlows() {
            return this.exitFlows;
        }

        @Override
        public String toString() {
            return "Exit{" +
                    "exitFlows=" + exitFlows +
                    '}';
        }
    }

}