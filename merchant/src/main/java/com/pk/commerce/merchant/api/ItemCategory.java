package com.pk.commerce.merchant.api;

public class ItemCategory {
    private Merchant merchant;
    private ItemCategoryName name;
    private ItemCategory parentCategory;

    public ItemCategory(Merchant merchant, ItemCategoryName name, ItemCategory parentCategory){
        this.merchant = merchant;
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public ItemCategoryName getName() {
        return name;
    }

    public void setName(ItemCategoryName name) {
        this.name = name;
    }

    public ItemCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ItemCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
}
