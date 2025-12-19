package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record MerchantEmail(String email) {
    @Override
    public @NonNull String toString() {
        return email;
    }
}
