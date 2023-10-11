package com.ekwateur.model;

import com.ekwateur.util.AccountIdGenerator;

public abstract class Customer {


    private final String accountId;

    private Consumption consumption;

    public Customer(Consumption consumption) {
        this.accountId = AccountIdGenerator.generateAccountId();
        this.consumption = consumption;

    }

    public String getAccountId() {
        return accountId;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }
}
