package com.pk.commerce.merchant.api;

public class Item {
    private ItemRef ref;
    private ItemPrice price;
    private ItemDiscount discount;

    public Item(ItemRef ref, ItemPrice price) {
        this.ref = ref;
        this.price = price;
    }

    public void applyDiscount(ItemDiscount discount){
        this.discount = discount;
    }

    public void removeDiscount() {
        this.discount = null;
    }

    public ItemPrice getPrice() {
        return price;
    }

    public ItemDiscount getDiscount(){
        return discount;
    }
}
