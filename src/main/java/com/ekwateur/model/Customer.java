package com.ekwateur.model;

import com.ekwateur.util.AccountIdGenerator;

public abstract class Customer {


    private String accountId;

    private EnergyType energyType;

    public Customer(EnergyType energyType) {
        this.accountId = AccountIdGenerator.generateAccountId();
        this.energyType = energyType;
    }


}
