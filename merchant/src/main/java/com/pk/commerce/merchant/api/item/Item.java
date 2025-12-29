package com.pk.commerce.merchant.api.item;

import com.pk.commerce.merchant.api.Image;

public class Item {
    private final ItemRef ref;
    private final ItemPrice price;
    private ItemDiscount discount;
    private Image image;

    public Item(ItemRef ref, ItemName itemName, ItemPrice price, Image image) {
        this.ref = ref;
        this.price = price;
        this.image = image;
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

    public void setDiscount(ItemDiscount discount) {
        this.discount = discount;
    }

    public ItemRef getRef() {
        return ref;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
