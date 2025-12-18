package com.pk.commerce.order.value;

import org.jspecify.annotations.NonNull;

public record OrderId(Long id) {
    @Override
    public @NonNull String toString() {
        return Long.toString(id);
    }
}
