package com.pk.commerce.orders.domain;

import com.pk.commerce.api.merchant.item.Item;
import com.pk.commerce.api.merchant.merchant.Merchant;
import com.pk.commerce.api.merchant.merchant.MerchantStatus;
import com.pk.commerce.orders.api.Order;
import com.pk.commerce.orders.db.OrderEntity;
import com.pk.commerce.orders.db.OrderRepository;
import com.pk.commerce.orders.rest.api.DirectCallMerchantProxy;
import com.pk.commerce.orders.rest.api.OrderRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService {
    private final DirectCallMerchantProxy merchantProxy;
    private final OrderRepository orderRepository;

    public OrderService(DirectCallMerchantProxy merchantProxy, OrderRepository orderRepository) {
        this.merchantProxy = merchantProxy;
        this.orderRepository = orderRepository;
    }

    public List<Order> ordersForMerchant(Long merchantRef) {
        List<OrderEntity> orderEntities = orderRepository.findOrdersForMerchant(merchantRef);
        return orderEntities.stream().map(OrderEntity::toOrder).toList();
    }

    public void orderItem(OrderRequest orderRequest) {
        Long itemRef = orderRequest.itemRef();
        //lookup item
        Item item = merchantProxy.getItemByRef(itemRef);

        Merchant merchant = getAndVerifyMerchant(item);
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
                merchant.getMerchantRef().refId(),
                count,
                new Timestamp(System.currentTimeMillis()),
                address1, address2, addressCity, postalCode, customerName, customerEmail, customerPhone);

        orderRepository.save(orderEntity);

    }

    public Item initiateOrder(Long itemRef) {
        //verify item
        Item item = merchantProxy.getItemByRef(itemRef);
        getAndVerifyMerchant(item);
        //return item details
        return item;
    }

    private Merchant getAndVerifyMerchant(Item item) {
        //verify merchant is active
        Merchant merchant = merchantProxy.getMerchantByRef(item.merchantRef());
        if (!merchant.getStatus().equals(MerchantStatus.ACTIVE)) {
            throw new IllegalArgumentException("Merchant is not active");
        }

        return merchant;
    }

    public Order findOrderByRefAndMerchant(Long orderRef, Long merchantRef) {
        return orderRepository.findOrderByRefAndMerchant(orderRef, merchantRef).orElseThrow(
                () -> new IllegalArgumentException("Unknown order")
        ).toOrder();
    }
}
