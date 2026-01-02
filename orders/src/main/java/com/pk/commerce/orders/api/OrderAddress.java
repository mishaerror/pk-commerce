package com.pk.commerce.orders.api;

public record OrderAddress(String customerName, String addressLineOne,
                           String addressLineTwo, String adressPostalCode,
                           String addressCity) {
}
