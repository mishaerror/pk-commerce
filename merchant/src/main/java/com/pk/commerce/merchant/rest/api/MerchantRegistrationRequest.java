package com.pk.commerce.merchant.rest.api;

public record MerchantRegistrationRequest(String name, String shopName, String contactPerson,
                                          String email, String phone, String address) {
}
