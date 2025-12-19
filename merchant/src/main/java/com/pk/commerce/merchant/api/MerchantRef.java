package com.pk.commerce.merchant.api;

import org.jspecify.annotations.NonNull;

public record MerchantRef(Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
