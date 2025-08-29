package com.udel.objectModel;

public class ObjectPlant {
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
    protected int modelMonthNumber;
    /**
     * Значение показателя ГНС на отопление
     */
    protected double GnsForHeat;

    public ObjectPlant(int id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    @Override
    public String toString() {
        return "ObjectPlant{" +
            "ID=" + ID +
            ", NAME='" + NAME + '\'' +
            ", modelMonthNumber=" + modelMonthNumber +
            ", GnsForHeat=" + GnsForHeat +
            '}';
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
