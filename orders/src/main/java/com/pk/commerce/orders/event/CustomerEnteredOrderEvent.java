package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class CustomerEnteredOrderEvent extends OrderEvent {
    public CustomerEnteredOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
