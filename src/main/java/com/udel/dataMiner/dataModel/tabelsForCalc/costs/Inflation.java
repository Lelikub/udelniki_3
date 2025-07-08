package com.udel.dataMiner.dataModel.tabelsForCalc.costs;

public class Inflation {
    public int Id;
    public String Name;
    public String Description;
    public double Proc;

    public Inflation() {
    }

    public Inflation(int Id, String Name, String Description, double Proc) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Proc = Proc;
    }
}
