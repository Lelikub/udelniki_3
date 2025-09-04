package com.udel.dataMiner.dataModel;

import java.util.List;

import com.udel.dataMiner.dataModel.enums.ObjectTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="objects")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int Id;

    @Column(name = "name")
    public String Name;

    @Column(name = "description")
    public String Description;

    @Enumerated(EnumType.STRING)
    @Column(name="object_type")
    public ObjectTypes ObjectType;

    @Column(name = "parent_object")
    public Object ParentObject;

    public Object() {
    }

    public Object(int Id, String Name, String Description, List<Line> Lines, ObjectTypes ObjectType, List<Item> Items) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.ObjectType = ObjectType;
    }


}
