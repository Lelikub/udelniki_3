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

    public Item() {
    }

    public Item(String Name) {
        this.Name = Name;
    }
    public Item(String Name, String Description) {
        this.Name = Name;
        this.Description = Description;
    }
}
