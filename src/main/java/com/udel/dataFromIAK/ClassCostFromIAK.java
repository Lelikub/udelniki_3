package com.udel.dataFromIAK;

public class ClassCostFromIAK {

    private String Name; 

    private double Cost;

    public ClassCostFromIAK() {
    }
    
    public void setName(String name){
        this.Name = name;
    }
    public String getName(){
        return Name;
    }

    public void setCost(double cost){
        this.Cost = cost;
    }

    public double getCost(){
        return Cost;
    }
}
