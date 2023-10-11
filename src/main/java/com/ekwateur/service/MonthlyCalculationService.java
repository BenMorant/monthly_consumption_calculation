package com.ekwateur.service;

import com.ekwateur.model.Company;
import com.ekwateur.model.Customer;
import com.ekwateur.model.EnergyType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;

public class MonthlyCalculationService {

    public static final String GAS_SME_RATE = "0.113";
    public static final String ELECTRICITY_LARGE_COMPANY_RATE = "0.114";
    public static final String GAS_LARGE_COMPANY_RATE = "0.111";
    public static final int SME_TO_LARGE_COMPANY_THRESHOLD = 1000000;
    private final static String ELECTRICITY_CONSUMER_RATE = "0.121";
    private final static String GAS_CONSUMER_RATE = "0.115";
    private final static String ELECTRICITY_SME_RATE = "0.118";

    public BigDecimal calculate(Customer customer, YearMonth yearMonth) {
        BigDecimal rate = getRate(customer);
        // yearLyQuantityKwh is rounded to the nearest kwh
        BigDecimal yearlyAmount = rate.multiply(customer.getConsumption().getYearlyQuantityKwh().setScale(0, RoundingMode.HALF_UP));
        // first multiply by the number of days in the month, then divide by the number of days in the year (to avoid rounding too soon), then round up to the nearest cent
        return yearlyAmount.multiply(BigDecimal.valueOf(yearMonth.lengthOfMonth())).divide(BigDecimal.valueOf(yearMonth.lengthOfYear()), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal getRate(Customer customer) {
        BigDecimal rate;
        if (customer instanceof Company company) {
            if (company.getRevenue() > SME_TO_LARGE_COMPANY_THRESHOLD) {
                rate = customer.getConsumption().getEnergyType().equals(EnergyType.ELECTRICITY) ? new BigDecimal(ELECTRICITY_LARGE_COMPANY_RATE) : new BigDecimal(GAS_LARGE_COMPANY_RATE);
            } else {
                rate = customer.getConsumption().getEnergyType().equals(EnergyType.ELECTRICITY) ? new BigDecimal(ELECTRICITY_SME_RATE) : new BigDecimal(GAS_SME_RATE);
            }
        } else {
            rate = customer.getConsumption().getEnergyType().equals(EnergyType.ELECTRICITY) ? new BigDecimal(ELECTRICITY_CONSUMER_RATE) : new BigDecimal(GAS_CONSUMER_RATE);
        }

        return rate;
    }


}
