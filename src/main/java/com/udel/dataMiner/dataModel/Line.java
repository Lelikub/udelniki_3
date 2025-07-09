package com.udel.dataMiner.dataModel;

import java.util.List;

import com.udel.dataMiner.dataModel.enums.Condition;

public class Line {
    public int Id;
    public String Name;
    public String Description;
    public String PlantName;
    public int PlantId;
    public List<Condition> Conditions;
    public List<Mode> Modes;
    public List<Item> Items;

    public Line() {
    }

    public Line(int Id, String Name, String Description, String PlantName, int PlantId, List<Condition> Conditions, List<Mode> Modes, List<Item> Items) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.PlantName = PlantName;
        this.PlantId = PlantId;
        this.Conditions = Conditions;
        this.Modes = Modes;
        this.Items = Items;
    }
}
