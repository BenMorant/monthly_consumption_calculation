package com.ekwateur.service;

import com.ekwateur.model.Company;
import com.ekwateur.model.Consumption;
import com.ekwateur.model.Customer;
import com.ekwateur.model.EnergyType;
import com.ekwateur.model.Individual;
import com.ekwateur.model.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.YearMonth;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonthlyCalculationServiceTest {

    public static final String GAS_SME_RATE = "0.113";
    public static final String ELECTRICITY_LARGE_COMPANY_RATE = "0.114";
    public static final String GAS_LARGE_COMPANY_RATE = "0.111";
    private final static String ELECTRICITY_CONSUMER_RATE = "0.121";
    private final static String GAS_CONSUMER_RATE = "0.115";
    private final static String ELECTRICITY_SME_RATE = "0.118";
    private static final BigDecimal YEARLY_QUANTITY = new BigDecimal("2200");
    private MonthlyCalculationService monthlyCalculationService;

    private static Stream<Arguments> provideParametersForCompany() {
        return Stream.of(
                Arguments.of(EnergyType.GAS, 1000001, GAS_LARGE_COMPANY_RATE),
                Arguments.of(EnergyType.ELECTRICITY, 1000001, ELECTRICITY_LARGE_COMPANY_RATE),
                Arguments.of(EnergyType.GAS, 100, GAS_SME_RATE),
                Arguments.of(EnergyType.ELECTRICITY, 100, ELECTRICITY_SME_RATE)
        );
    }

    @Test
    void calculate_for_individual_electricity_january_2023() {
        // GIVEN
        Consumption consumption = new Consumption(EnergyType.ELECTRICITY, YEARLY_QUANTITY);
        Customer customer = new Individual(Title.OTHER, "Toto", "Titi", consumption);
        BigDecimal expected = new BigDecimal(ELECTRICITY_CONSUMER_RATE).multiply(YEARLY_QUANTITY).multiply(BigDecimal.valueOf(31)).divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);
        monthlyCalculationService = new MonthlyCalculationService();
        //  WHEN
        BigDecimal result = monthlyCalculationService.calculate(customer, YearMonth.of(2023, Month.JANUARY));
        // THEN
        assertEquals(expected, result);

    }

    @Test
    void calculate_for_individual_gas_february_2024() {
        // GIVEN
        Consumption consumption = new Consumption(EnergyType.GAS, YEARLY_QUANTITY);
        Customer customer = new Individual(Title.OTHER, "Toto", "Titi", consumption);
        BigDecimal expected = new BigDecimal(GAS_CONSUMER_RATE).multiply(YEARLY_QUANTITY).multiply(BigDecimal.valueOf(29)).divide(BigDecimal.valueOf(366), 2, RoundingMode.HALF_UP);
        monthlyCalculationService = new MonthlyCalculationService();
        //  WHEN
        BigDecimal result = monthlyCalculationService.calculate(customer, YearMonth.of(2024, Month.FEBRUARY));
        // THEN
        assertEquals(expected, result);

    }

    @ParameterizedTest
    @MethodSource("provideParametersForCompany")
    void calculate_for_company_february_2024(EnergyType energyType, long revenue, String expectedRate) {
        // GIVEN
        Consumption consumption = new Consumption(energyType, YEARLY_QUANTITY);
        Customer customer = new Company(generateFakeSiret(), "The Company", revenue, consumption);
        BigDecimal expected = new BigDecimal(expectedRate).multiply(YEARLY_QUANTITY).multiply(BigDecimal.valueOf(29)).divide(BigDecimal.valueOf(366), 2, RoundingMode.HALF_UP);
        monthlyCalculationService = new MonthlyCalculationService();
        //  WHEN
        BigDecimal result = monthlyCalculationService.calculate(customer, YearMonth.of(2024, Month.FEBRUARY));
        // THEN
        assertEquals(expected, result);

    }

    private long generateFakeSiret() {
        Random random = new Random();
        long min = 100_000_000_000_00L;
        long max = 999_999_999_999_99L;

        return random.nextLong() % (max - min + 1) + max;
    }
}

