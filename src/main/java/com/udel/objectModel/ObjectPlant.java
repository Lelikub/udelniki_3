package com.udel.objectModel;

public class ObjectPlant extends ObjectModel {

    public ObjectPlant(int id, String name) {
        super(id,name);
    }

    @Override
    public String toString() {
        return "ObjectPlant{" +
            "ID=" + ID +
            ", NAME='" + NAME + '\'' +
            '}';
    }
}
