package com.pk.commerce.merchant.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class MerchantRefFilter extends OncePerRequestFilter {

    public static final String X_MERCHANT_REF = "X-Merchant-Ref";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String merchantRef = request.getHeader(X_MERCHANT_REF);
            MerchantRequestContext.setMerchantRef(merchantRef);
            // Proceed with the request
            filterChain.doFilter(request, response);
        } finally {
            // Ensure cleanup happens even if an exception occurs
            MerchantRequestContext.clear();
        }
    }
}
