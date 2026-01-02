package com.pk.commerce.orders.domain;

import com.pk.commerce.orders.api.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectOrderProxy implements OrderProxy {
    private final OrderService orderService;

    public DirectOrderProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<Order> getMerchantOrders(Long merchantRef) {
        return orderService.ordersForMerchant(merchantRef);
    }

    @Override
    public Order getOrderByRef(Long orderRef, Long merchantRef) {
        return orderService.findOrderByRefAndMerchant(orderRef, merchantRef);
    }
}
