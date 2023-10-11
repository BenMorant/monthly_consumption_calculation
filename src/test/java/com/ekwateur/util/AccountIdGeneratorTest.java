package com.ekwateur.util;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountIdGeneratorTest {

    private static final String GENERATION_REGEX = "^EKW\\d{8}$";


    @Test
    void generateAccountI_should_return_EKW_8_digits() {

        String accountId = AccountIdGenerator.generateAccountId();

        boolean isMatch = Pattern.compile(GENERATION_REGEX).matcher(accountId).matches();
        assertTrue(isMatch, "AccountId format is incorrect");
    }

}

