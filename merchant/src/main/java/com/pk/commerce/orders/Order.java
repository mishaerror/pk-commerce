package com.pk.commerce.orders;


import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;

import java.time.LocalDateTime;

public class Order {
    private OrderRef orderRef;
    private OrderState state;
    private OrderAddress orderAddress;
    private OrderContact orderContact;
    private Integer count;
    private Amount orderTotal;
    private CurrencyCode orderCurrency;
    private LocalDateTime orderTime;

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

    public OrderContact getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(OrderContact orderContact) {
        this.orderContact = orderContact;
    }

    public Amount getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Amount orderTotal) {
        this.orderTotal = orderTotal;
    }

    public CurrencyCode getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(CurrencyCode orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
