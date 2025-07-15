package com.udel.dataMiner.dataModel.tabelsForCalc.costs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="inflation")
public class Inflation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Column(name = "procent")
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
