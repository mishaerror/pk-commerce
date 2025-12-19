package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record ItemRef(Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
