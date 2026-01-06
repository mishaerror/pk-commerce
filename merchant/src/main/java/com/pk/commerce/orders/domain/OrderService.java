package com.pk.commerce.orders.domain;

import com.pk.commerce.merchant.api.item.Item;
import com.pk.commerce.merchant.api.merchant.MerchantStatus;
import com.pk.commerce.merchant.domain.item.ItemService;
import com.pk.commerce.orders.Order;
import com.pk.commerce.orders.db.OrderEntity;
import com.pk.commerce.orders.db.OrderRepository;
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

        orderRepository.save(orderEntity);

    }

    public Item initiateOrder(Long itemRef) {
        //verify item
        Item item = itemService.findByRef(itemRef);
        verifyMerchant(item);
        //return item details
        return item;
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
