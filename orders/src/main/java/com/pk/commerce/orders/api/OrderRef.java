package com.pk.commerce.orders.api;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record OrderRef(@JsonValue Long id) {
    @Override
    public @NonNull String toString() {
        return Long.toString(id);
    }
}
