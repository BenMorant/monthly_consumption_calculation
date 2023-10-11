package com.ekwateur.model;

import java.math.BigDecimal;

public class Consumption {

    private EnergyType energyType;

    private BigDecimal yearlyQuantityKwh;

    public Consumption(EnergyType energyType, BigDecimal yearlyQuantityKwh) {
        this.energyType = energyType;
        this.yearlyQuantityKwh = yearlyQuantityKwh;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public BigDecimal getYearlyQuantityKwh() {
        return yearlyQuantityKwh;
    }

    public void setYearlyQuantityKwh(BigDecimal yearlyQuantityKwh) {
        this.yearlyQuantityKwh = yearlyQuantityKwh;
    }
}
