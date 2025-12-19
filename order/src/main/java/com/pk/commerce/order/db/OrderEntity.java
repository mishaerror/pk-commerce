package com.pk.commerce.order.db;

import com.pk.commerce.order.state.OrderState;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("orders")
public class OrderEntity {
    @Id
    private Long id;//db identity primary key

    private Long orderRef;//reference separately generated from sequence generator

    private String orderState;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String merchantRef;

    public OrderEntity(Long orderRef, String merchantRef, Timestamp createdAt) {
        this.orderRef = orderRef;
        this.merchantRef = merchantRef;
        this.createdAt = createdAt;
        this.orderState = OrderState.CUSTOMER_INITIATED.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(Long orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(String merchantRef) {
        this.merchantRef = merchantRef;
    }
}
