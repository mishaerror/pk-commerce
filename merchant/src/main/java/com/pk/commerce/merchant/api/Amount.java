package com.pk.commerce.merchant.api;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public record Amount(@JsonValue BigDecimal amount) {

}
