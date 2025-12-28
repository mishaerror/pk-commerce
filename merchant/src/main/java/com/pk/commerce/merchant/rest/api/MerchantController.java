package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.domain.admin.MerchantRegistrationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantRegistrationRequest request) {
        // Save to database
        if (merchantRegistrationService.merchantEmailExists(request.getMerchantEmail()).isEmpty()) {
            return ResponseEntity.unprocessableContent().build();
        }
        Long merchantRef = merchantRegistrationService.updateMerchantRegistrationRequest(request.getMerchantName(), request.getMerchantShopName(), request.getMerchantContactPerson(),
                request.getMerchantEmail(), request.getMerchantPhone(), request.getMerchantAddress());

        return ResponseEntity.ok(merchantRef);
    }
}