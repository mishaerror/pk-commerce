package com.pk.commerce.merchant.api.merchant;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NonNull;

public record MerchantContactPerson(@JsonValue String name) {
    @Override
    public @NonNull String toString() {
        return name;
    }
}
