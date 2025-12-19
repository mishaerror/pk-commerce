package com.pk.commerce.merchant.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortUrlTest {
    @Test
    void shouldEncode(){
        assertEquals("a", ShortUrl.encode(BigInteger.ZERO));
        assertEquals("ba", ShortUrl.encode(BigInteger.valueOf(52)));
        assertEquals("bb", ShortUrl.encode(BigInteger.valueOf(53)));
        assertEquals("bbba", ShortUrl.encode(BigInteger.valueOf(52 + 52 * 52 + 52 * 52 * 52)));
        assertEquals("bbbb", ShortUrl.encode(BigInteger.valueOf( 1 + 52 + 52*52 + 52*52*52  )));
        assertEquals("bacb", ShortUrl.encode(BigInteger.valueOf(1 + 2 * 52 + 52 * 52 * 52)));
    }
}
