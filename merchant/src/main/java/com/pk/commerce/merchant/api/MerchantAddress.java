package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record MerchantAddress(String address) {
    @Override
    public @NonNull String toString() {
        return address;
    }
}
