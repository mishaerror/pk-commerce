package com.pk.commerce.merchant.utils;

import java.math.BigInteger;

//Encodes given integer into base52-format, where digits are upper and lowercase letters
public class ShortUrl {
    private static final char[] mapping = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String encode(BigInteger number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();

        final BigInteger FIFTY_TWO = BigInteger.valueOf(52);

        do {
            BigInteger[] divideAndRemainder = number.divideAndRemainder(FIFTY_TWO);

           result.insert(0, mapping[divideAndRemainder[1].intValue()]);

            number = divideAndRemainder[0];
        } while (number.compareTo(BigInteger.ZERO) > 0);

        return result.toString();
    }
}
