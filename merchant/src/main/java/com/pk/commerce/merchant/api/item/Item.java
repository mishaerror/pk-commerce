package com.pk.commerce.merchant.api.item;

import com.pk.commerce.merchant.api.merchant.MerchantRef;
import com.pk.commerce.merchant.api.merchant.MerchantStatus;

import java.awt.*;

public record Item(ItemRef ref, ItemName name, Integer count, ItemPrice price, ItemDiscount discount, Image image,
                   MerchantRef merchantRef, MerchantStatus merchantStatus) {

}
