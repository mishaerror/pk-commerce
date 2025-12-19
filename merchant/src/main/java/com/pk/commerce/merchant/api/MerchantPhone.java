package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record MerchantPhone(String phone) {
    @Override
    public @NonNull String toString() {
        return phone;
    }
}
