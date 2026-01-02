package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class MerchantSentOrderEvent extends OrderEvent {
    public MerchantSentOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
