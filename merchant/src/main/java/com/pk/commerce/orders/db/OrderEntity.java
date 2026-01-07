package com.pk.commerce.orders.db;

import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;
import com.pk.commerce.orders.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Table("orders")
public class OrderEntity {
    @Id
    private Long id;//db identity primary key

    private Long ref;//reference separately generated from sequence generator

    private String state;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Long merchantRef;

    private String addressLineOne;

    private String addressLineTwo;

    private String addressCity;

    private String addressPostalCode;

    private String customerName;

    private String customerEmail;

    private Integer count;

    private String customerPhone;

    private BigDecimal totalAmount;

    private String totalCurrency;

    private Timestamp orderTime;

    public OrderEntity(Long ref, Long merchantRef, Integer count, Timestamp createdAt, String addressLineOne, String addressLineTwo, String addressCity, String addressPostalCode, String customerName, String customerEmail, String customerPhone) {
        this.ref = ref;
        this.merchantRef = merchantRef;
        this.createdAt = createdAt;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.addressCity = addressCity;
        this.addressPostalCode = addressPostalCode;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.state = OrderState.CUSTOMER_INITIATED.name();
        this.count = count;
    }

    public Order toOrder() {
        Order order = new Order();

        order.setOrderRef(new OrderRef(this.ref));
        order.setCount(this.count);
        order.setState(OrderState.valueOf(this.state));
        order.setOrderAddress(
                new OrderAddress(this.customerName, this.addressLineOne, this.addressLineTwo, this.addressPostalCode, this.addressCity)
        );
        order.setOrderContact(new OrderContact(this.customerPhone, this.customerEmail));
        order.setOrderTotal(new Amount(this.totalAmount));
        order.setOrderCurrency(CurrencyCode.valueOf(this.totalCurrency));
        order.setOrderTime(this.orderTime.toLocalDateTime());

        return order;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Long getMerchantRef() {
        return merchantRef;
    }

    public void setMerchantRef(Long merchantRef) {
        this.merchantRef = merchantRef;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(String totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
}
