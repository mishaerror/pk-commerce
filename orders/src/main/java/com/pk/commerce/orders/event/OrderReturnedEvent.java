package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class OrderReturnedEvent extends OrderEvent {
    public OrderReturnedEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
