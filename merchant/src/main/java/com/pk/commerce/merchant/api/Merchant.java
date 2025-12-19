package com.pk.commerce.merchant.api;

public class Merchant {
    private MerchantStatus status;
    private MerchantRef merchantRef;
    private MerchantName merchantName;//personal or legal name - could be pulled from email auth data, with edit option
    private MerchantContactPerson merchantContactPerson;
    private MerchantShopName merchantShopName; //custom shop name displayed to customers
    private MerchantAddress merchantAddress;
    private MerchantPhone merchantPhone;
    private MerchantEmail merchantEmail;

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    public MerchantRef getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(MerchantRef merchantRef) {
        this.merchantRef = merchantRef;
    }

    public MerchantName getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(MerchantName merchantName) {
        this.merchantName = merchantName;
    }

    public MerchantShopName getMerchantShopName() {
        return merchantShopName;
    }

    public void setMerchantShopName(MerchantShopName merchantShopName) {
        this.merchantShopName = merchantShopName;
    }

    public MerchantAddress getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(MerchantAddress merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public MerchantPhone getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(MerchantPhone merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public MerchantEmail getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(MerchantEmail merchantEmail) {
        this.merchantEmail = merchantEmail;
    }

    public MerchantContactPerson getMerchantContactPerson() {
        return merchantContactPerson;
    }

    public void setMerchantContactPerson(MerchantContactPerson merchantContactPerson) {
        this.merchantContactPerson = merchantContactPerson;
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

        public Builder status(String status){
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

        public Merchant build() {
            Merchant merchant = new Merchant();

            if(this.status != null) {
                merchant.setStatus(MerchantStatus.valueOf(status));
            }

            if(this.merchantRef != null) {
                merchant.setMerchantRef(new MerchantRef(this.merchantRef));
            }


            if(this.merchantName != null) {
                merchant.setMerchantName(new MerchantName(this.merchantName));
            }


            if(this.merchantShopName != null) {
                merchant.setMerchantShopName(new MerchantShopName(this.merchantShopName));
            }

            if(this.contactPerson != null) {
                merchant.setMerchantContactPerson(new MerchantContactPerson(contactPerson));
            }

            if(this.merchantAddress != null) {
                merchant.setMerchantAddress(new MerchantAddress(this.merchantAddress));
            }


            if(this.merchantPhone != null) {
                merchant.setMerchantPhone(new MerchantPhone(this.merchantPhone));
            }


            if(this.merchantEmail != null) {
                merchant.setMerchantEmail(new MerchantEmail(this.merchantEmail));
            }

            return merchant;
        }
    }
}
