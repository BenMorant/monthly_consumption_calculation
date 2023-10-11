package com.ekwateur.service;

import com.ekwateur.model.Customer;
import com.ekwateur.model.EnergyType;
import com.ekwateur.model.Individual;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonthlyCalculationServiceTest {

    private MonthlyCalculationService monthlyCalculationService;

    @Test
    void calculate_for_individual_customer_electricity_january_2023() {
        // GIVEN
        Customer customer = new Individual("xxx", EnergyType.ELECTRICITY);
        monthlyCalculationService = new MonthlyCalculationService();
        Integer expected = null;
        //  WHEN
        Integer result = monthlyCalculationService.calculate(customer);
        // THEN
        assertEquals(expected, result);

    }

}