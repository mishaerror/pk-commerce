package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class MerchantDeclinedOrderEvent extends OrderEvent {
    public MerchantDeclinedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
