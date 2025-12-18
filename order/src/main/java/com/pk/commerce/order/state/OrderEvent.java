package com.pk.commerce.order.state;

public abstract class OrderEvent {
    protected final OrderEventType type;
    protected final Long timestamp;
    public OrderEvent(OrderEventType eventType, Long timestamp) {
        this.type = eventType;
        this.timestamp = timestamp;
    }
}
