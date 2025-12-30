package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.api.item.ItemDiscount;

import java.math.BigDecimal;

public record ItemRequest(String ref, String name, ItemPrice price, ItemDiscount discount, String image) {
    record ItemPrice(BigDecimal amount, String currency) {
    }
}
