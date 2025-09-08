package com.udel.dataMiner.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name= "model_parameters")
public class ModelParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    ModelParameters()
    {

    }

    public ModelParameters(String Name) {
        this.Name = Name;
    }
}
