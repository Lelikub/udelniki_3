package com.udel.dataMiner.dataModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "modes")
public class Mode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @ManyToOne
    @JoinColumn(name = "object_id")
    public ObjectEntity Object;

    public Mode() {
    }

    public Mode(int Id, String Name, ObjectEntity Object) {
        this.Id = Id;
        this.Name = Name;
        this.Object = Object;
    }
    public Mode(String Name, ObjectEntity Object) {
        this.Name = Name;
        this.Object = Object;
    }
}
