package com.pk.commerce.merchant.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("categories")
public class CategoryEntity {
    @Id
    private Long id;

    private Long ref;

    private String name;

    private Long merchantRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(Long merchantRef) {
        this.merchantRef = merchantRef;
    }
}
