package com.pk.commerce.merchant.rest.api;

public class MerchantRegistrationRequest {

    private String merchantName;
    private String merchantShopName;
    private String merchantContactPerson;
    private String merchantPhone;
    private String merchantEmail;
    private String merchantAddress;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantShopName() {
        return merchantShopName;
    }

    public void setMerchantShopName(String merchantShopName) {
        this.merchantShopName = merchantShopName;
    }

    public String getMerchantContactPerson() {
        return merchantContactPerson;
    }

    public void setMerchantContactPerson(String merchantContactPerson) {
        this.merchantContactPerson = merchantContactPerson;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(String merchantEmail) {
        this.merchantEmail = merchantEmail;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }
}
