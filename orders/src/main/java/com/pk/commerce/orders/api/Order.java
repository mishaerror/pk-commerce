package com.pk.commerce.orders.api;

import com.pk.commerce.orders.state.OrderState;

public class Order {
    private OrderRef orderRef;
    private OrderState state;

    public Order() {
        state = OrderState.CUSTOMER_INITIATED;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderRef getOrderRef() {
        return orderRef;
    }

    public void setOrderId(OrderRef orderRef) {
        this.orderRef = orderRef;
    }
}
