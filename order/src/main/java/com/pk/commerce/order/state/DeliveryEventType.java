package com.pk.commerce.order.state;

public enum DeliveryEventType {
    PREPARED,
    READY_FOR_PICKUP,
    PICKED_UP,
    IN_TRANSIT,
    PENDING_DELIVERY,
    COULD_NOT_DELIVER,
    DELIVERED,
    RETURNED_TO_SENDER
}
