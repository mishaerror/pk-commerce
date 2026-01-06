package com.pk.commerce.orders.domain;

import com.pk.commerce.merchant.api.item.Item;
import com.pk.commerce.merchant.api.merchant.MerchantStatus;
import com.pk.commerce.merchant.domain.item.ItemService;
import com.pk.commerce.orders.Order;
import com.pk.commerce.orders.OrderRef;
import com.pk.commerce.orders.OrderState;
import com.pk.commerce.orders.db.OrderEntity;
import com.pk.commerce.orders.db.OrderRepository;
import com.pk.commerce.orders.event.*;
import com.pk.commerce.orders.rest.api.OrderActionRequest;
import com.pk.commerce.orders.rest.api.OrderRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    public List<Order> ordersForMerchant(Long merchantRef) {
        List<OrderEntity> orderEntities = orderRepository.findOrdersForMerchant(merchantRef);
        return orderEntities.stream().map(OrderEntity::toOrder).toList();
    }

    public void orderItem(OrderRequest orderRequest) {
        Long itemRef = orderRequest.itemRef();
        //lookup item
        Item item = itemService.findByRef(itemRef);

        verifyMerchant(item);
        //verify availability
        Integer count = orderRequest.count();

        if (item.count() < count) {
            throw new IllegalArgumentException("Not enough items in inventory");
        }

        String address1 = orderRequest.addressLineOne();
        String address2 = orderRequest.addressLineTwo();
        String addressCity = orderRequest.addressCity();
        String postalCode = orderRequest.addressPostalCode();
        String customerEmail = orderRequest.email();
        String customerName = orderRequest.customerName();
        String customerPhone = orderRequest.phone();

        OrderEntity orderEntity = new OrderEntity(
                orderRepository.refSequence(),
                item.merchantRef().refId(),
                count,
                new Timestamp(System.currentTimeMillis()),
                address1, address2, addressCity, postalCode, customerName, customerEmail, customerPhone);
        orderEntity.setState(OrderState.CUSTOMER_ENTERED.name());

        orderRepository.save(orderEntity);

    }

    public Item initiateOrder(Long itemRef) {
        //verify item
        Item item = itemService.findByRef(itemRef);
        verifyMerchant(item);
        //return item details
        return item;
    }

    public OrderState orderAction(OrderActionRequest.Action action, Long orderRef) {
        OrderEntity orderEntity = orderRepository.findOrderByRef(orderRef).orElseThrow(() -> new IllegalArgumentException("Unknown order"));
        OrderRef ref = new OrderRef(orderRef);

        OrderState orderState = OrderState.valueOf(orderEntity.getState());
        var event = switch (action) {
            case SEND -> new MerchantSentOrderEvent(ref, System.currentTimeMillis());
            case RETURN -> new OrderReturnedEvent(ref, System.currentTimeMillis());
            case CONFIRM -> new MerchantConfirmedOrderEvent(ref, System.currentTimeMillis());
            case DECLINE -> new MerchantDeclinedOrderEvent(ref, System.currentTimeMillis());
            case DELIVER -> new OrderDeliveredEvent(ref, System.currentTimeMillis());
        };
        OrderState nextState = orderState.nextState(event);

        orderEntity.setState(nextState.name());

        orderRepository.save(orderEntity);

        return nextState;
    }

    private void verifyMerchant(Item item) {
        //verify merchant is active
        if (!MerchantStatus.ACTIVE.equals(item.merchantStatus())) {
            throw new IllegalArgumentException("Merchant is not active");
        }
    }

    public Order findOrderByRefAndMerchant(Long orderRef, Long merchantRef) {
        return orderRepository.findOrderByRefAndMerchant(orderRef, merchantRef).orElseThrow(
                () -> new IllegalArgumentException("Unknown order")
        ).toOrder();
    }
}
