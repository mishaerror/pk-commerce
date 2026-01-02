package com.pk.commerce.orders.api;

import com.pk.commerce.orders.state.OrderState;

public class Order {
    private OrderRef orderRef;
    private OrderState state;
    private OrderAddress orderAddress;
    private Integer count;

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

    public void setOrderRef(OrderRef orderRef) {
        this.orderRef = orderRef;
    }

    public void setOrderId(OrderRef orderRef) {
        this.orderRef = orderRef;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
