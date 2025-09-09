package com.udel.objectModel;

import java.util.HashMap;

public abstract class ObjectModel {
    /**
     * ID объекта
     */
    final int ID;
    /**
     * Название объекта
     */
    final String NAME;
    /**
     * Параметры по перечню из базы данных
     */
    final HashMap<Integer,Double> objectModelParameters = new HashMap<>();

    public ObjectModel(int ID, String NAME) {
        this.ID = ID;
        this.NAME = NAME;
    }

    public HashMap<Integer,Double> getObjectModelParameters(){
        return objectModelParameters;
    }
    public void addObjectModelParameter(Integer parameter, Double value) {
        objectModelParameters.put(parameter,value);
    }

    public Double getObjectModelParameter(Integer paramId){
        return objectModelParameters.getOrDefault(paramId,0.0);
    }

    public int getId() {
        return this.ID;
    }

    public String getName() {
        return this.NAME;
    }
}
