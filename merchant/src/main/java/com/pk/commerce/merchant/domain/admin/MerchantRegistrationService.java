package com.pk.commerce.merchant.domain.admin;

import com.pk.commerce.merchant.api.MerchantStatus;
import com.pk.commerce.merchant.db.MerchantEntity;
import com.pk.commerce.merchant.db.MerchantRepository;
import com.pk.commerce.merchant.exception.EmailNotUniqueException;
import com.pk.commerce.merchant.exception.NameNotUniqueException;
import com.pk.commerce.merchant.exception.ShopNameNotUniqueException;
import org.springframework.stereotype.Service;

@Service
public class MerchantRegistrationService {
    private final MerchantRepository merchantRepository;

    public MerchantRegistrationService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    //registration request - merchant in pending status
    public void addMerchantRegistrationRequest(String name, String shopName,
                                               String contactPerson,
                                               String email, String phone, String address) {

        //TODO validate uniqueness of email and name, shop name
        if(merchantRepository.findByName(name).isPresent()) {
            throw new NameNotUniqueException();
        }
        if(merchantRepository.findByEmail(email).isPresent()) {
            throw new EmailNotUniqueException();
        }
        if(merchantRepository.findByShopName(shopName).isPresent()) {
            throw new ShopNameNotUniqueException();
        }



        MerchantEntity merchantEntity = new MerchantEntity.Builder()
                .merchantName(name)
                .merchantRef(merchantRepository.refSequence())
                .status(MerchantStatus.PENDING.name())
                .contactPerson(contactPerson)
                .merchantShopName(shopName)
                .merchantEmail(email)
                .merchantPhone(phone)
                .merchantAddress(address)
                .build();


        merchantRepository.save(merchantEntity);
    }

    public void approveMerchantRegistration() {

    }

    public void rejectMerchantRegistration() {

    }

}
