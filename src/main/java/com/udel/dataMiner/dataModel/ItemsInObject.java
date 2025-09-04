package com.udel.dataMiner.dataModel;

import jakarta.persistence.*;

@Entity
@Table(name = "items_in_objects")
public class ItemsInObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    // Ссылка на ObjectEntity
    @ManyToOne
    @JoinColumn(name = "object_id", nullable = false)
    private ObjectEntity object;

    // Ссылка на Item
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    // Способ расчёта
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "method_id", nullable = false)
    private CalculationMethod calculationMethod;

    public ItemsInObject() {}

    public ItemsInObject(ObjectEntity object, Item item, CalculationMethod calculationMethod) {
        this.object = object;
        this.item = item;
        this.calculationMethod = calculationMethod;
    }

    // геттеры/сеттеры
    public int getId() { return Id; }

    public ObjectEntity getObject() { return object; }
    public void setObject(ObjectEntity object) { this.object = object; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public CalculationMethod getCalculationMethod() { return calculationMethod; }
    public void setCalculationMethod(CalculationMethod calculationMethod) { this.calculationMethod = calculationMethod; }

}
