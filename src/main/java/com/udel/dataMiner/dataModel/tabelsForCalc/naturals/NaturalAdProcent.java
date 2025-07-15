package com.udel.dataMiner.dataModel.tabelsForCalc.naturals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="natural_ad_procent")
public class NaturalAdProcent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Column(name = "procent")
    public double Procent;

    @Column(name = "natural")
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
