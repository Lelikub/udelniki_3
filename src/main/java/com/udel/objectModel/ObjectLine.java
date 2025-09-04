package com.udel.objectModel;

import com.udel.dataMiner.dataModel.enums.ConditionType;

public class ObjectLine extends ObjectModel {

    /**
     * ID режима в котором работает линия
     */
    private int modeId;
    /**
     * Состояние линии активна/ремонт/неактивна
     */
    private ConditionType conditionType;


    public ObjectLine(int id, String name, Integer modeId, ConditionType conditionType) {
        super(id, name);
        this.modeId = modeId;
        this.conditionType = conditionType;
    }

    @Override
    public String toString() {
        return "ObjectLine{" +
            "ID=" + ID +
            ", NAME='" + NAME +
            ", modeId=" + modeId +
            ", conditionType=" + conditionType +
            '}';
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }
}
