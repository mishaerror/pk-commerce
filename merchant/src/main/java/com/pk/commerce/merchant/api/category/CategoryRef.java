package com.pk.commerce.merchant.api.category;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record CategoryRef(@JsonValue String refId) {
    @Override
    public @NonNull String toString() {
        return refId;
    }
}
