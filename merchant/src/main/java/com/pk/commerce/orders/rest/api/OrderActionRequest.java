package com.pk.commerce.orders.rest.api;

public record OrderActionRequest(Action action) {
    public enum Action {
        CONFIRM, DECLINE, SEND, DELIVER, RETURN
    }
}
