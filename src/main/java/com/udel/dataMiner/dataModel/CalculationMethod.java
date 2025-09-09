package com.udel.dataMiner.dataModel;

import com.udel.dataMiner.dataModel.enums.CalculationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "calculation_methods")
public class CalculationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "method")
    private CalculationType type;

    @Column(name = "description")
    private String description;

    public CalculationMethod() {}

    public CalculationMethod(CalculationType type) {
        this.type = type;
    }

    public CalculationMethod(CalculationType type, String description)
    {
        this.type = type;
        this.description=description;
    }

    public CalculationType getType() {
        return type;
    }

    public void setType(CalculationType type) {
        this.type = type;
    }

    // геттеры/сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


}
