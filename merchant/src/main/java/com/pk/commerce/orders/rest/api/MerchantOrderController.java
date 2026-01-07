package com.pk.commerce.orders.rest.api;

import com.pk.commerce.config.MerchantRequestContext;
import com.pk.commerce.orders.Order;
import com.pk.commerce.orders.OrderState;
import com.pk.commerce.orders.domain.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/{orderRef}/actions")
    public ResponseEntity<?> postAction(@PathVariable("orderRef") Long orderRef, @RequestBody OrderActionRequest actionRequest) {
        OrderState nextState = orderService.orderAction(actionRequest.action(), orderRef);
        return ResponseEntity.ok(nextState);
    }

    @GetMapping
    public ResponseEntity<List<?>> getOrders(@RequestParam(name = "days", defaultValue = "10") Integer days,
                                             @RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,
                                             @RequestParam(value = "dateTo", required = false) LocalDate dateTo,
                                             @RequestParam(value = "status", required = false) OrderState orderState) {
        if (dateFrom == null) {
            dateFrom = LocalDate.now().minusDays(days);
        }
        if (dateTo == null) {
            dateTo = LocalDate.now();
        }
        String stateParam = orderState != null ? orderState.name() : "%";

        List<Order> orders = orderService.ordersForMerchant(MerchantRequestContext.getMerchantRefLong(), dateFrom, dateTo, stateParam);

        return ResponseEntity.ok(orders);
    }
}
