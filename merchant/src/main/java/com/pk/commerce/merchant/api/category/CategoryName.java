package com.pk.commerce.merchant.api.category;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record CategoryName(@JsonValue String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
