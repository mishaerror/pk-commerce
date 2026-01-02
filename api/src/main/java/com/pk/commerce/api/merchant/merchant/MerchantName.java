package com.pk.commerce.api.merchant.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantName(@JsonValue String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
