package com.pk.commerce.merchant.api;

public record ItemCategory(Merchant merchant, ItemCategoryName name, ItemCategory parentCategory) {

}
