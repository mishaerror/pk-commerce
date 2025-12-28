package com.pk.commerce.merchant.api.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantAddress(@JsonValue String address) {
    @Override
    public @NonNull String toString() {
        return address;
    }
}
