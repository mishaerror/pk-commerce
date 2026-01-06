package com.pk.commerce.orders.event;

import com.pk.commerce.orders.OrderRef;

public class CustomerEnteredOrderEvent extends OrderEvent {
    public CustomerEnteredOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
