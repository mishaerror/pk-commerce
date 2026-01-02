package com.pk.commerce.orders.rest.api;

import com.pk.commerce.orders.domain.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class CustomerOrderController {
    private final OrderService orderService;

    public CustomerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> orderItem(@RequestBody OrderRequest orderRequest) {
        orderService.orderItem(orderRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderRef}")
    public ResponseEntity<?> getOrder(@PathVariable(name = "orderRef") Long itemRef) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/item/{itemRef}")
    public ResponseEntity<?> initiateOrder(@PathVariable(name = "itemRef") Long itemRef) {

        return ResponseEntity.ok(orderService.initiateOrder(itemRef));
    }
}
