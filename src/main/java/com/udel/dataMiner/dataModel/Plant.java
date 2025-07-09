package com.udel.dataMiner.dataModel;

import java.util.List;

import com.udel.dataMiner.dataModel.enums.PlantGroup;


public class Plant {
    public int Id;
    public String Name;
    public String Description;
    public List<Line> Lines;
    public PlantGroup PlantGroup;
    public List<Item> Items;

    public Plant() {
    }

    public Plant(int Id, String Name, String Description, List<Line> Lines, PlantGroup PlantGroup, List<Item> Items) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Lines = Lines;
        this.PlantGroup = PlantGroup;
        this.Items = Items;
    }
}
