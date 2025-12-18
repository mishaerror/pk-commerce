package com.pk.commerce.order.rest;

import com.pk.commerce.order.state.DeliveryEventType;
import com.pk.commerce.order.api.OrderId;

public record DeliveryEvent(OrderId orderId, DeliveryEventType eventType, String comment, Long timestamp) {
}
