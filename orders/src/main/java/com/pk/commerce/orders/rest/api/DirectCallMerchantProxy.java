package com.pk.commerce.orders.rest.api;

import com.pk.commerce.api.merchant.item.Item;
import com.pk.commerce.api.merchant.merchant.Merchant;
import com.pk.commerce.api.merchant.merchant.MerchantRef;
import com.pk.commerce.merchant.domain.item.ItemService;
import com.pk.commerce.merchant.domain.merchant.MerchantService;
import org.springframework.stereotype.Component;

@Component
public class DirectCallMerchantProxy implements MerchantProxy {
    private final ItemService itemService;
    private final MerchantService merchantService;

    public DirectCallMerchantProxy(ItemService itemService, MerchantService merchantService) {
        this.itemService = itemService;
        this.merchantService = merchantService;
    }

    @Override
    public Item getItemByRef(Long ref) {
        return itemService.findByRef(ref);
    }

    @Override
    public Merchant getMerchantByRef(MerchantRef merchantRef) {
        return merchantService.getDetails(merchantRef);
    }
}
