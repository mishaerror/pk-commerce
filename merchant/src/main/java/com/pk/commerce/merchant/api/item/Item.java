package com.pk.commerce.merchant.api.item;

public class Item {
    private final ItemRef ref;
    private final ItemPrice price;
    private ItemDiscount discount;

    public Item(ItemRef ref, ItemPrice price) {
        this.ref = ref;
        this.price = price;
    }

    public void applyDiscount(ItemDiscount discount) {
        this.discount = discount;
    }

    public void removeDiscount() {
        this.discount = null;
    }

    public ItemPrice getPrice() {
        return price;
    }

    public ItemDiscount getDiscount() {
        return discount;
    }
}
