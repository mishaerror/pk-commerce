package com.pk.commerce.api.merchant.item;

import com.pk.commerce.api.merchant.merchant.MerchantRef;

import java.awt.*;

public record Item(ItemRef ref, ItemName name, Integer count, ItemPrice price, ItemDiscount discount, Image image, MerchantRef merchantRef) {

}
