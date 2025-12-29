package com.pk.commerce.merchant.api.item;

import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;

public class ItemPrice {
    private final CurrencyCode currencyCode;
    private final Amount amount;
    private ItemDiscount discount;

    public ItemPrice(Amount amount, CurrencyCode currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }


    public ItemPrice applyDiscount(ItemDiscount itemDiscount) {
        this.discount = itemDiscount;
        return this;
    }

    public CurrencyCode getCurrency() {
        return currencyCode;
    }

    public Amount getAmount() {
        return amount;
    }

    public ItemDiscount getDiscount() {
        return discount;
    }
}
