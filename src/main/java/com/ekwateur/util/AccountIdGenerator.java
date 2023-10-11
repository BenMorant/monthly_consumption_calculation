package com.ekwateur.util;

import java.util.Random;

public class AccountIdGenerator {


    private static final String PREFIX = "EKW";


    public static String generateAccountId() {
        Random random = new Random();
        int min = 10000000;
        int max = 99999999;
        int randomInt = random.nextInt(max - min + 1) + min;

        return PREFIX + String.format("%08d", randomInt);
    }

}
