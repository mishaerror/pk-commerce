package com.pk.commerce.api.merchant.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantRef(@JsonValue Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
