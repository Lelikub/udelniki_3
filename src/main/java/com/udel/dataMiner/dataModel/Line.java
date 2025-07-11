package com.udel.dataMiner.dataModel;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Lines")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name="name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Column(name = "plant_name")
    public String PlantName;

    @Column(name = "plant_id")
    public int PlantId;

    @ManyToOne
    @JoinColumn(name = "plant_id", insertable = false, updatable = false)
    public Plant Plant;

    /*@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "line_condition",
            joinColumns = @JoinColumn(name = "line_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )*/
    @Transient
    public List<Condition> Conditions;

    @OneToMany(mappedBy = "Line", cascade = CascadeType.ALL, orphanRemoval= true)
    public List<Mode> Modes;

    @OneToMany(mappedBy = "Line", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Item> Items;

    public Line() {
    }

    public Line(int Id, String Name, String Description, String PlantName, int PlantId, Plant plant, List<Condition> Conditions, List<Mode> Modes, List<Item> Items) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.PlantName = PlantName;
        this.PlantId = PlantId;
        this.Plant = plant;
        this.Conditions = Conditions;
        this.Modes = Modes;
        this.Items = Items;
    }
}
