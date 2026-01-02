package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class CustomerAbandonedOrderEvent extends OrderEvent {
    public CustomerAbandonedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
