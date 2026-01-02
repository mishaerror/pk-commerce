package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.config.MerchantRequestContext;
import com.pk.commerce.orders.api.Order;
import com.pk.commerce.orders.domain.DirectOrderProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//merchant order management
@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {
    private final DirectOrderProxy directOrderProxy;

    public OrderController(DirectOrderProxy directOrderProxy) {
        this.directOrderProxy = directOrderProxy;
    }

    @GetMapping("/{orderRef}")
    public ResponseEntity<?> getOrderByRef(@PathVariable("orderRef") Long orderRef) {
        Order order = directOrderProxy.getOrderByRef(orderRef, MerchantRequestContext.getMerchantRefLong());
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<?>> getOrders() {
        List<Order> orders = directOrderProxy.getMerchantOrders(MerchantRequestContext.getMerchantRefLong());
        return ResponseEntity.ok(orders);
    }
    //view and filter orders
    //update order status
}
