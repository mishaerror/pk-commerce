package com.pk.commerce.api.merchant.item;

import com.pk.commerce.api.merchant.Amount;
import com.pk.commerce.api.merchant.CurrencyCode;

public record ItemPrice(Amount amount, CurrencyCode currency) {
}
