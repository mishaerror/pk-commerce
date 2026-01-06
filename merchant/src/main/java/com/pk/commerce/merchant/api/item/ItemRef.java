package com.pk.commerce.merchant.api.item;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record ItemRef(@JsonValue Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
