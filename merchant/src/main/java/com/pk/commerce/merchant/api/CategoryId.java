package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record CategoryId(Long id) {
    @Override
    public @NonNull String toString() {
        return Long.toString(id);
    }
}
