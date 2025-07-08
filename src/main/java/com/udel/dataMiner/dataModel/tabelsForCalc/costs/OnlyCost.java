package com.udel.dataMiner.dataModel.tabelsForCalc.costs;

public class OnlyCost {
    public int Id;
    public String Name;
    public String Description;
    public double Cost;

    public OnlyCost(){}

    public OnlyCost(int Id, String Name, String Description, double Cost) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Cost = Cost;
    }
}