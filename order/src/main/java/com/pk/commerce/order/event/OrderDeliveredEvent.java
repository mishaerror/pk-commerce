package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class OrderDeliveredEvent extends OrderEvent {
    public OrderDeliveredEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
