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
@Table(name = "conditions")
public class  Condition{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @ManyToOne
    @JoinColumn(name = "object_id")
    public ObjectEntity Object;

    public Condition() {
    }

    public Condition(int Id, String Name, String Description, ObjectEntity Object) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Object = Object;
    }
    public Condition(String Name, String Description, ObjectEntity Object) {
        this.Name = Name;
        this.Description = Description;
        this.Object = Object;
    }
}
