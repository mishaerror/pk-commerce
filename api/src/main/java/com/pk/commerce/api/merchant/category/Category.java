package com.pk.commerce.api.merchant.category;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Category {
    private final CategoryRef ref;
    private CategoryName name;
    private Category parent;

    @JsonCreator
    public Category(CategoryName name, Category parent, CategoryRef ref) {
        this.name = name;
        this.parent = parent;
        this.ref = ref;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public CategoryRef getRef() {
        return ref;
    }
}
