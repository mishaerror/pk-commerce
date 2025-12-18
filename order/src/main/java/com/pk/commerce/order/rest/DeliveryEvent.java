package com.pk.commerce.order.domain;

import com.pk.commerce.order.value.DeliveryEventType;
import com.pk.commerce.order.value.OrderId;

public record DeliveryEvent(OrderId orderId, DeliveryEventType eventType, String comment, Long timestamp) {
}
