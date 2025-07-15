package com.udel.dataMiner.dataModel.tabelsForCalc.naturals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="only_procent")
public class OnlyProcent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Nmae;

    @Column(name = "description")
    public String Description;

    @Column(name = "procent")
    public double Procent;

    public OnlyProcent() {
    }

    public OnlyProcent(int Id, String Nmae, String Description, double Procent) {
        this.Id = Id;
        this.Nmae = Nmae;
        this.Description = Description;
        this.Procent = Procent;
    }
}
