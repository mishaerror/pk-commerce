package com.pk.commerce.api.merchant.item;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record ItemName(@JsonValue String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
