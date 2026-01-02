package com.pk.commerce.orders.domain;

import com.pk.commerce.orders.api.Order;

import java.util.List;

public interface OrderProxy {
    List<Order> getMerchantOrders(Long merchantRef);

    Order getOrderByRef(Long orderRef, Long merchantRef);
}
