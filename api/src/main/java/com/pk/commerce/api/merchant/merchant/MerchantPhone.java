package com.pk.commerce.api.merchant.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantPhone(@JsonValue String phone) {
    @Override
    public @NonNull String toString() {
        return phone;
    }
}
