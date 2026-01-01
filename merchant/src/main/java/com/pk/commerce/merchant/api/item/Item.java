package com.pk.commerce.merchant.api.item;

import com.pk.commerce.merchant.api.Image;

public record Item(ItemRef ref, ItemName name, Integer count, ItemPrice price, ItemDiscount discount, Image image) {

}
