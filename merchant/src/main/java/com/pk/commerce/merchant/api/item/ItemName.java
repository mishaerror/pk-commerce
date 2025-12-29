package com.pk.commerce.merchant.api.item;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record ItemName(@JsonValue String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
