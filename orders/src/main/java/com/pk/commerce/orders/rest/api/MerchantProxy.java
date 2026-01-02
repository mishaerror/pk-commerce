package com.pk.commerce.orders.rest.api;

import com.pk.commerce.api.merchant.item.Item;
import com.pk.commerce.api.merchant.merchant.Merchant;
import com.pk.commerce.api.merchant.merchant.MerchantRef;

public interface MerchantProxy {
    Item getItemByRef(Long ref);

    Merchant getMerchantByRef(MerchantRef merchantRef);
}
