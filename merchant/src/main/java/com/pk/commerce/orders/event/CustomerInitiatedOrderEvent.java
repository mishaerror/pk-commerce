package com.pk.commerce.orders.event;

import com.pk.commerce.orders.OrderRef;

public class CustomerInitiatedOrderEvent extends OrderEvent {
    public CustomerInitiatedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
