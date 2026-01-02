package com.pk.commerce.api.merchant.item;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public record ItemDiscount(@JsonValue BigDecimal percent) {
}
