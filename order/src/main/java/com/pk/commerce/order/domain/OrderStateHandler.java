package com.pk.commerce.order.domain;

import com.pk.commerce.order.api.Order;
import com.pk.commerce.order.event.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderStateHandler {
    public void handleOrderEvent(Order order, OrderEvent event) {
        order.setState(order.getState().nextState(event));
    }
}
