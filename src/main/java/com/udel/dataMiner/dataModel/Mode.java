package com.udel.dataMiner.dataModel;

public class Mode {
    public int Id;
    public String Name;
    public int PlantId;
    public String PlantName;

    public Mode() {
    }

    public Mode(int Id, String Name, int PlantId, String PlantName) {
        this.Id = Id;
        this.Name = Name;
        this.PlantId = PlantId;
        this.PlantName = PlantName;
    }
}
