package com.pk.commerce.order.state;

import com.pk.commerce.order.api.Order;
import com.pk.commerce.order.api.OrderState;

public class OrderStateHandler {
    public OrderState nextState(Order order, OrderEvent event) {
        switch (order.getState()) {
            default -> {
                return null;
            }
        }
    }
}
