package com.pk.commerce.orders.domain;

import com.pk.commerce.orders.api.Order;
import com.pk.commerce.orders.event.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderStateHandler {
    public void handleOrderEvent(Order order, OrderEvent event) {
        order.setState(order.getState().nextState(event));
    }
}
