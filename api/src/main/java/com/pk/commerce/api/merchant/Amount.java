package com.pk.commerce.api.merchant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public record Amount(@JsonValue BigDecimal amount) {

}
