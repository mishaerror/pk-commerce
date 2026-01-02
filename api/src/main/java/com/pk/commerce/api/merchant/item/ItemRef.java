package com.pk.commerce.api.merchant.item;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record ItemRef(@JsonValue Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
