package com.udel.dataMiner.dataModel.tabelsForCalc.naturals;

public class NaturalAdProcent {
    public int Id;
    public String Name;
    public String Description;
    public double Procent;
    public double Natural;

    public NaturalAdProcent() {
    }

    public NaturalAdProcent(int Id, String Name, String Description, double Procent, double Natural) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Procent = Procent;
        this.Natural = Natural;
    }
}
