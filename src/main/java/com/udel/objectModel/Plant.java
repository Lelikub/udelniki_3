package com.udel.objectModel;

public class Plant {
    /**
     * ID установки
     */
    final int ID;
    /**
     * Название установки
     */
    final String NAME;
    /**
     * Текущий номер модельного месяца
     */
    private int modelMonthNumber;
    /**
     * Значение показателя ГНС на отопление
     */
    private double GnsForHeat;

    public Plant(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public int getId() {
        return this.ID;
    }

    public String getName() {
        return this.NAME;
    }

    public void setModelMonthNumber(int modelMonthNumber) {
        this.modelMonthNumber = modelMonthNumber;
    }

    public void setGnsForHeat(double gnsForHeat) {
        GnsForHeat = gnsForHeat;
    }

    public double getGnsForHeat() {
        return GnsForHeat;
    }

    public int getModelMonthNumber() {
        return modelMonthNumber;
    }
}
