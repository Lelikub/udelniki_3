package com.udel.objectModel;

public class ObjectLine extends ObjectPlant {

    /**
     * ID установки к которой принадлежит линия
     */
    private final int plant_ID;
    /**
     * ID режима в котором работает линия
     */
    private int modeId;
    /**
     * Состояние линии активна/неактивна
     */
    private boolean isEnable;


    public ObjectLine(int id, String name, ObjectPlant plant) {
        super(id, name);
        this.plant_ID = plant.getId();
        this.modelMonthNumber = plant.getModelMonthNumber();
    }

    @Override
    public String toString() {
        return "ObjectLine{" +
            "ID=" + ID +
            ", NAME='" + NAME +
            ", plant_ID=" + plant_ID +
            ", modeId=" + modeId +
            ", isEnable=" + isEnable +
            ", modelMonthNumber=" + modelMonthNumber +
            ", GnsForHeat=" + GnsForHeat + '\'' +
            '}';
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getPlant_ID() {
        return plant_ID;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }
}
