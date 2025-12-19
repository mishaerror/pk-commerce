package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class CustomerEnteredOrderEvent extends OrderEvent {
    public CustomerEnteredOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
