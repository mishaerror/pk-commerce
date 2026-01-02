package com.pk.commerce.api.merchant.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantAddress(@JsonValue String address) {
    @Override
    public @NonNull String toString() {
        return address;
    }
}
