package com.udel.dataMiner.dataModel.tabelsForCalc.costs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="only_cost")
public class OnlyCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Column(name = "cost")
    public double Cost;

    public OnlyCost(){}

    public OnlyCost(int Id, String Name, String Description, double Cost) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Cost = Cost;
    }
}