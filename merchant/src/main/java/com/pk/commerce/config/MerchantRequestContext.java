package com.pk.commerce.config;

public class MerchantRequestContext {
    // Make ThreadLocal static and final
    private static final ThreadLocal<String> merchantRef = new ThreadLocal<>();

    public static String getMerchantRef() {
        return merchantRef.get();
    }

    public static void setMerchantRef(String id) {
        merchantRef.set(id);
    }

    public static Long getMerchantRefLong() {
        return Long.valueOf(merchantRef.get());
    }

    // IMPORTANT: Always clear the ThreadLocal value to prevent memory leaks
    public static void clear() {
        merchantRef.remove();
    }
}
