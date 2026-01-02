package com.pk.commerce.orders.rest.api;

public record OrderRequest(Long itemRef, Integer count, String customerName, String addressLineOne,
                           String addressLineTwo,
                           String addressCity, String addressPostalCode, String email, String phone) {
}
