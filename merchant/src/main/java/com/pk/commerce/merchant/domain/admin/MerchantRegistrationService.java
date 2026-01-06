package com.pk.commerce.merchant.domain.admin;


import com.pk.commerce.merchant.api.merchant.MerchantStatus;
import com.pk.commerce.merchant.db.MerchantEntity;
import com.pk.commerce.merchant.db.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantRegistrationService {
    private final MerchantRepository merchantRepository;

    public MerchantRegistrationService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Optional<MerchantEntity> merchantEmailExists(String email) {
        return merchantRepository.findByEmail(email);
    }

    //registration request - merchant in pending status
    public Long addMerchantRegistrationRequest(String name, String shopName,
                                               String contactPerson,
                                               String email, String phone, String address) {

        //TODO validate uniqueness of email shop name


        MerchantEntity merchantEntity;
        if (merchantRepository.findByEmail(email).isEmpty()) {
            merchantEntity = new MerchantEntity.Builder()
                    .merchantName(name)
                    .merchantRef(merchantRepository.refSequence())
                    .status(MerchantStatus.PENDING.name())
                    .contactPerson(contactPerson)
                    .merchantShopName(shopName)
                    .merchantEmail(email)
                    .merchantPhone(phone)
                    .merchantAddress(address)
                    .build();
        } else {
            merchantEntity = merchantRepository.findByEmail(email).get();
            merchantEntity.setStatus(shopName);
            merchantEntity.setName(name);
            merchantEntity.setContactPerson(contactPerson);
            merchantEntity.setPhone(phone);
            merchantEntity.setStatus(MerchantStatus.ACTIVE.name());
        }


        return merchantRepository.save(merchantEntity).getRef();
    }

    //registration request - merchant in pending status
    public Long updateMerchantRegistrationRequest(String name, String shopName,
                                                  String contactPerson,
                                                  String email, String phone, String address) {

        //TODO validate uniqueness of email shop name


        MerchantEntity merchantEntity = merchantRepository.findByEmail(email).get();
        merchantEntity.setStatus(shopName);
        merchantEntity.setName(name);
        merchantEntity.setContactPerson(contactPerson);
        merchantEntity.setPhone(phone);
        merchantEntity.setStatus(MerchantStatus.ACTIVE.name());


        return merchantRepository.save(merchantEntity).getRef();
    }

    public void approveMerchantRegistration() {

    }

    public void rejectMerchantRegistration() {

    }

}
