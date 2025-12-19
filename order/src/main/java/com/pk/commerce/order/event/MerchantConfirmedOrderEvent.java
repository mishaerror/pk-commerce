package com.pk.commerce.order.event;

import com.pk.commerce.order.api.OrderRef;

public class MerchantConfirmedOrderEvent extends OrderEvent {
    public MerchantConfirmedOrderEvent(OrderRef orderRef, Long timestamp) {
        super(orderRef, timestamp);
    }
}
