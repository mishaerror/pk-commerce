package com.pk.commerce.api.merchant.category;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record CategoryRef(@JsonValue String refId) {
    @Override
    public @NonNull String toString() {
        return refId;
    }
}
