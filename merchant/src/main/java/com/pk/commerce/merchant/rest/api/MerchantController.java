package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantRegistrationService merchantRegistrationService;

    public MerchantController(MerchantRegistrationService merchantRegistrationService) {
        this.merchantRegistrationService = merchantRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantRegistrationRequest request,
                                              @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        // Create merchant record

        // Save to database
        Long merchantRef = merchantRegistrationService.updateMerchantRegistrationRequest(request.name(), request.shopName(), request.contactPerson(),
                request.email(), request.phone(), request.address());
        
        return ResponseEntity.ok(merchantRef);
    }
}