package com.udel.dataMiner.dataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // Связь с ItemsInObjects
    @OneToMany(mappedBy = "object", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemsInObject> itemsInObject = new ArrayList<>();

    @OneToMany(mappedBy = "Object", cascade = CascadeType.ALL, orphanRemoval= true, fetch= FetchType.EAGER)
    private List<Condition> Conditions = new ArrayList<>();

    @OneToMany(mappedBy = "Object", cascade = CascadeType.ALL, orphanRemoval= true, fetch= FetchType.EAGER)
    private List<Mode> Modes = new ArrayList<>();

    public ObjectEntity() {
    }

    public ObjectEntity(String Name, String Description, ObjectTypes ObjectType) {
        this.Name = Name;
        this.Description = Description;
        this.ObjectType = ObjectType;
    }
    public ObjectEntity(String Name, String Description, ObjectTypes ObjectType, ObjectEntity parentObject) {
        this.Name = Name;
        this.Description = Description;
        this.ObjectType = ObjectType;
        this.ParentObject = parentObject;
    }

    // ✅ Геттер/сеттер
    public List<ItemsInObject> getItemsInObjects() {
        return itemsInObject;
    }

    public void setItemsInObjects(List<ItemsInObject> itemsInObjects) {
        this.itemsInObject = itemsInObjects;
    }

    public void addItem(Item item, CalculationMethod method) {
        ItemsInObject link = new ItemsInObject(this, item, method);
        itemsInObject.add(link);
    }

    public void addItems(Map<Item, CalculationMethod> itemsWithMethods) {
        for (Map.Entry<Item, CalculationMethod> entry : itemsWithMethods.entrySet()) {
            ItemsInObject link = new ItemsInObject(this, entry.getKey(), entry.getValue());
            itemsInObject.add(link);
        }
    }
    public List<Mode> getModesInObjects() {
        return Modes;
    }
    public List<Condition> getConditionsInObjects() {
        return Conditions;
    }

    public void addConditions(List<Condition> conditions) {
        for (Condition condition  : conditions) {
            Condition c = new Condition(0, condition.Name, condition.Description, condition.Object);
            Conditions.add(c);
        }
    }

    public void addModes(List<Mode> modes) {
        for (Mode mode  : modes) {
            Mode m = new Mode(0, mode.Name, mode.Object);
            Modes.add(m);
        }
    }
}
