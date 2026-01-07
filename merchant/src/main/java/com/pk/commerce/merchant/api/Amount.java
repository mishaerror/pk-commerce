package com.pk.commerce.merchant.api;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Amount(@JsonValue BigDecimal amount) {
    public Amount multiply(Integer mult) {
        return new Amount(this.amount.multiply(BigDecimal.valueOf(mult)));
    }

    public Amount subtract(BigDecimal m) {
        return new Amount(this.amount.subtract(m));
    }

    public Amount subtract(Amount m) {
        return new Amount(this.amount.subtract(m.amount));
    }

    public Amount add(BigDecimal m) {
        return new Amount(this.amount.add(m));
    }

    public Amount add(Amount m) {
        return new Amount(this.amount.add(m.amount));
    }

    public Amount percent(BigDecimal m) {
        return new Amount(this.amount.multiply(m).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
    }

}
