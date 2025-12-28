package com.pk.commerce.merchant.api.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantEmail(@JsonValue String email) {
    @Override
    public @NonNull String toString() {
        return email;
    }
}
