package com.pk.commerce.orders;

public record OrderAddress(String customerName, String addressLineOne,
                           String addressLineTwo, String adressPostalCode,
                           String addressCity) {
}
