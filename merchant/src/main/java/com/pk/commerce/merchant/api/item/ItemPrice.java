package com.pk.commerce.merchant.api.item;

import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;

public record ItemPrice(Amount amount, CurrencyCode currencyCode) {
}
