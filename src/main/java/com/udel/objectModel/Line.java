package com.udel.objectModel;

public class Line extends Plant{

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


    public Line(int id, String name, int plant_ID) {
        super(id, name);
        this.plant_ID=plant_ID;
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
