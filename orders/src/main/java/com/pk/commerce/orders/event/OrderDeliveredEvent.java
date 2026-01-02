package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class OrderDeliveredEvent extends OrderEvent {
    public OrderDeliveredEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
