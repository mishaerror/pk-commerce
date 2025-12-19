package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record MerchantShopName(String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
