package com.pk.commerce.merchant.api.item;

import com.fasterxml.jackson.annotation.JsonValue;

public record ItemDiscount(@JsonValue Integer percent) {
}
