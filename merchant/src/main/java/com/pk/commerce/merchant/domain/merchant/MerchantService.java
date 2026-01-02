package com.pk.commerce.merchant.domain.merchant;

import com.pk.commerce.api.merchant.merchant.Merchant;
import com.pk.commerce.api.merchant.merchant.MerchantRef;
import com.pk.commerce.merchant.db.MerchantRepository;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant getDetails(MerchantRef merchantRef) {
        return
                merchantRepository
                        .findByRef(merchantRef.refId()).orElseThrow(() -> new IllegalArgumentException("Merchant not found"))
                        .toMerchant();
    }

    public void editDetails() {

    }

    public void disableShop() {

    }

    public void removeShop() {

    }
}
