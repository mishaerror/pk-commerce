package com.pk.commerce.order.rest;

import com.pk.commerce.order.api.OrderRef;

public record DeliveryEvent(OrderRef orderRef, String comment, Long timestamp) {
}
