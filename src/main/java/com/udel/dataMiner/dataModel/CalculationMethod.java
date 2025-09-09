package com.udel.dataMiner.dataModel;

import com.udel.dataMiner.dataModel.enums.CalculationType;
import jakarta.persistence.*;

@Entity
@Table(name = "calculation_methods")
public class CalculationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private CalculationType type;

    public CalculationMethod() {}

    public CalculationMethod(CalculationType type) {
        this.type = type;
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
