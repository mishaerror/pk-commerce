package com.pk.commerce.orders.rest.api;

import com.pk.commerce.config.MerchantRequestContext;
import com.pk.commerce.orders.Order;
import com.pk.commerce.orders.domain.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//merchant order management
@RestController
@RequestMapping("/api/admin/orders")
public class MerchantOrderController {
    private final OrderService orderService;

    public MerchantOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderRef}")
    public ResponseEntity<?> getOrderByRef(@PathVariable("orderRef") Long orderRef) {
        Order order = orderService.findOrderByRefAndMerchant(orderRef, MerchantRequestContext.getMerchantRefLong());
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<?>> getOrders() {
        List<Order> orders = orderService.ordersForMerchant(MerchantRequestContext.getMerchantRefLong());
        return ResponseEntity.ok(orders);
    }
    //view and filter orders
    //update order status
}
