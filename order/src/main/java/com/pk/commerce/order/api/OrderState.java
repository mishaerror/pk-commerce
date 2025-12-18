package com.pk.commerce.order.value;

public enum OrderState {
    CUSTOMER_INITIATED,//customer opens the page
    CUSTOMER_ABANDONED,//Customer didn't complete process during timeframe
    CUSTOMER_ENTERED,//Customer entered the order
    MERCHANT_CONFIRMED,
    MERCHANT_DECLINED,
    MERCHANT_PREPARED,
    MERCHANT_SENT,
    PENDING_DELIVERY,
    DELIVERED_TO_CUSTOMER,
    RETURNED_TO_MERCHANT
}
