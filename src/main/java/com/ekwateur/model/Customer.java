package com.ekwateur.model;

public abstract class Customer {


    private String accountId;

    private EnergyType energyType;

    public Customer(String accountId, EnergyType energyType) {
        this.accountId = accountId;
        this.energyType = energyType;
    }


}
