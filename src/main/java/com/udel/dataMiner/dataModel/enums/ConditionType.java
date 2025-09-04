package com.udel.dataMiner.dataModel.enums;

public enum ConditionType {
    Work("Работа"),
    Repair("Ремонт"),
    TurnedOff("Простой");

    private final String displayName;

    ConditionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
