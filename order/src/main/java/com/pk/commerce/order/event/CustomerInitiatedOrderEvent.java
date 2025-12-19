package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class CustomerInitiatedOrderEvent extends OrderEvent {
    public CustomerInitiatedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
