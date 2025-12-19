package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class MerchantDeclinedOrderEvent extends OrderEvent {
    public MerchantDeclinedOrderEvent(OrderRef orderRef,  Long timestamp) {
        super(orderRef, timestamp);
    }
}
