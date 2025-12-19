package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class OrderReturnedEvent extends OrderEvent {
    public OrderReturnedEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
