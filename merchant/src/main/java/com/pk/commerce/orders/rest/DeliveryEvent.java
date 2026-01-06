package com.pk.commerce.orders.rest;

import com.pk.commerce.orders.OrderRef;

public record DeliveryEvent(OrderRef orderRef, String comment, Long timestamp) {
}
