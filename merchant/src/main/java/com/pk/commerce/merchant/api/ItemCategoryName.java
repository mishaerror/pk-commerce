package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record ItemCategoryName(String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
