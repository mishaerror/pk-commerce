package com.pk.commerce.order.domain;

import com.pk.commerce.order.value.ItemCategoryName;

public record ItemCategory(Merchant merchant, ItemCategoryName name, ItemCategory parentCategory) {

}
