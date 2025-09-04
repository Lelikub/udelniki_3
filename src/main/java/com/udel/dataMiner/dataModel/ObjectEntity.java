package com.udel.dataMiner.dataModel;

import java.util.List;

import com.udel.dataMiner.dataModel.enums.ObjectTypes;

import jakarta.persistence.*;

@Entity
@Table(name="objects")
public class ObjectEntity {

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

    // 🔹 Ссылка на родителя
    @ManyToOne
    @JoinColumn(name = "parent_object", nullable = true)
    public ObjectEntity ParentObject;

    public ObjectEntity() {
    }

    public ObjectEntity(int Id, String Name, String Description, ObjectTypes ObjectType) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.ObjectType = ObjectType;
    }


}
