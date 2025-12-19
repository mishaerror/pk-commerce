package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class MerchantSentOrderEvent extends OrderEvent {
    public MerchantSentOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
