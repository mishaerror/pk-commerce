package com.pk.commerce.merchant.rest.api;

import java.math.BigDecimal;

public record ItemRequest(Long ref, String name, ItemPrice price, String discount, String image, Integer count) {
    record ItemPrice(BigDecimal amount, String currency) {
    }
}
