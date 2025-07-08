package com.udel.dataMiner.dataModel;

import com.udel.dataMiner.dataModel.enums.TypeOfCost;
import com.udel.dataMiner.dataModel.enums.TypeOfNatural;

public class Item {
    public int Id;
    public String Name;
    public String Description;
    public TypeOfNatural NaturalCalc;
    public TypeOfCost CostCalc;

    public Item() {
    }

    public Item(int Id, String Name, String Description, TypeOfNatural NaturalCalc, TypeOfCost CostCalc) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.NaturalCalc = NaturalCalc;
        this.CostCalc = CostCalc;
    }
}