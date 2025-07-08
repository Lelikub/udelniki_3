package com.udel.dataMiner.dataModel.tabelsForCalc.costs;

public class CostAdKoef {
    public int Id;
    public String Name;
    public String Description;
    public double Koef;
    public double Cost;

    public CostAdKoef() {
    }

    public CostAdKoef(int Id, String Name, String Description, double Koef, double Cost) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Koef = Koef;
        this.Cost = Cost;
    }
}
