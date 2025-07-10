package com.udel.dataMiner.dataModel;

import java.util.List;

import com.udel.dataMiner.dataModel.enums.PlantGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @OneToMany(mappedBy="Plant", cascade=CascadeType.ALL, orphanRemoval= true)
    public List<Line> Lines;

    @Enumerated(EnumType.STRING)
    @Column(name="plant_group")
    public PlantGroup PlantGroup;

    @OneToMany(mappedBy="Plant", cascade=CascadeType.ALL, orphanRemoval= true)
    public List<Item> Items;

    public Plant() {
    }

    public Plant(int Id, String Name, String Description, List<Line> Lines, PlantGroup PlantGroup, List<Item> Items) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Lines = Lines;
        this.PlantGroup = PlantGroup;
        this.Items = Items;
    }


}
