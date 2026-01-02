package com.pk.commerce.orders.event;

import com.pk.commerce.orders.api.OrderRef;

public class MerchantConfirmedOrderEvent extends OrderEvent {
    public MerchantConfirmedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
