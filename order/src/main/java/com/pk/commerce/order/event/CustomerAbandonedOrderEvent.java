package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class CustomerAbandonedOrderEvent extends OrderEvent {
    public CustomerAbandonedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
