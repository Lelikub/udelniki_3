package com.udel.dataMiner.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="objects_parameters")
public class ObjectParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Column(name = "procent")
    public Double Procent;

    @Column(name = "natural")
    public Double Natural;

    @Column(name = "cost")
    public Double Cost;

    @Column(name = "koeff")
    public Double Koef;

    public ObjectParameters() {
    }

    public ObjectParameters(int Id, String Name, String Description, double Natural, double Procent, double Cost, double Koef) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Procent = Procent;
        this.Natural = Natural;
        this.Cost = Cost;
        this.Koef = Koef;
    }

    public ObjectParameters(int Id, String Name, String Description, double Procent, double Cost) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Procent = Procent;
        this.Cost = Cost;
    }

    public ObjectParameters(int Id, String Name, String Description, double Cost) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Cost = Cost;
    }

}
