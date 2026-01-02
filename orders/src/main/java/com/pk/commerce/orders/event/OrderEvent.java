package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public abstract class OrderEvent {
    protected final Long timestamp;
    protected final OrderRef orderRef;

    public OrderEvent(OrderRef orderRef, Long timestamp) {
        this.timestamp = timestamp;
        this.orderRef = orderRef;
    }
}
