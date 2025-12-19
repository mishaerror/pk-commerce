package com.pk.commerce.merchant.api;

public class ItemPrice {
    private final Currency currency;
    private final Amount amount;
    private ItemDiscount discount;

    public ItemPrice(Amount amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }


    public ItemPrice applyDiscount(ItemDiscount itemDiscount) {
        this.discount = itemDiscount;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Amount getAmount() {
        return amount;
    }

    public ItemDiscount getDiscount() {
        return discount;
    }
}
