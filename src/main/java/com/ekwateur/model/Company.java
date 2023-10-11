package com.ekwateur.model;

public class Company extends Customer {

    // SIRET
    private final long directoryId;

    private final String companyName;

    private long revenue;

    public Company(long directoryId, String companyName, long revenue, Consumption consumption) {
        super(consumption);
        this.directoryId = directoryId;
        this.companyName = companyName;
        this.revenue = revenue;
    }



    public String getCompanyName() {
        return companyName;
    }


    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }
}
