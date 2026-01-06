package com.pk.commerce.merchant.api.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantRef(@JsonValue Long refId) {
    @Override
    public @NonNull String toString() {
        return Long.toString(refId);
    }
}
