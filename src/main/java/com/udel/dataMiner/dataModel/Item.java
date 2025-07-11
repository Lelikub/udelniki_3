package com.udel.dataMiner.dataModel;

import com.udel.dataMiner.dataModel.enums.TypeOfCost;
import com.udel.dataMiner.dataModel.enums.TypeOfNatural;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Enumerated(EnumType.STRING)
    @Column(name = "natural_calc")
    public TypeOfNatural NaturalCalc;

    @Enumerated(EnumType.STRING)
    @Column(name = "cost_calc")
    public TypeOfCost CostCalc;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="plant_id")
    public Plant Plant;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="line_id")
    public Line Line;

    public Item() {
    }

    public Item(int Id, String Name, String Description, TypeOfNatural NaturalCalc, TypeOfCost CostCalc, Plant Plant, Line Line) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.NaturalCalc = NaturalCalc;
        this.CostCalc = CostCalc;
        this.Plant = Plant;
        this.Line = Line;
    }
}