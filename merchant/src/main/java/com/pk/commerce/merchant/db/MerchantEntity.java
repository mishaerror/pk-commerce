package com.pk.commerce.merchant.db;

import com.pk.commerce.merchant.api.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("merchants")
public class MerchantEntity {
    @Id
    private Long id;

    private String status;
    private Long ref;
    private String name;
    private String shopName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Merchant toMerchant() {
        return new Merchant.Builder()
                .contactPerson(this.contactPerson)
                .merchantRef(this.ref)
                .status(this.status)
                .merchantPhone(this.phone)
                .merchantEmail(this.email)
                .merchantAddress(this.address)
                .merchantShopName(this.shopName)
                .merchantName(this.name)
                .build();
    }

    public static class Builder {
        private String status;
        private Long merchantRef;
        private String merchantName;//personal or legal name - could be pulled from email auth data, with edit option
        private String merchantShopName; //custom shop name displayed to customers
        private String contactPerson;
        private String merchantAddress;
        private String merchantPhone;
        private String merchantEmail;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder merchantRef(Long merchantRef) {
            this.merchantRef = merchantRef;
            return this;
        }

        public Builder contactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public Builder merchantName(String merchantName) {
            this.merchantName = merchantName;
            return this;
        }

        public Builder merchantShopName(String merchantShopName) {
            this.merchantShopName = merchantShopName;
            return this;
        }

        public Builder merchantAddress(String merchantAddress) {
            this.merchantAddress = merchantAddress;
            return this;
        }

        public Builder merchantPhone(String merchantPhone) {
            this.merchantPhone = merchantPhone;
            return this;
        }

        public Builder merchantEmail(String merchantEmail) {
            this.merchantEmail = merchantEmail;
            return this;
        }

        public MerchantEntity build() {
            MerchantEntity merchant = new MerchantEntity();

            if (this.status != null) {
                merchant.setStatus(status);
            }

            if (this.merchantRef != null) {
                merchant.setRef(this.merchantRef);
            }


            if (this.merchantName != null) {
                merchant.setName(this.merchantName);
            }


            if (this.merchantShopName != null) {
                merchant.setShopName(this.merchantShopName);
            }

            if (this.contactPerson != null) {
                merchant.setContactPerson(contactPerson);
            }

            if (this.merchantAddress != null) {
                merchant.setAddress(this.merchantAddress);
            }


            if (this.merchantPhone != null) {
                merchant.setPhone(this.merchantPhone);
            }


            if (this.merchantEmail != null) {
                merchant.setEmail(this.merchantEmail);
            }

            return merchant;
        }
    }
}
