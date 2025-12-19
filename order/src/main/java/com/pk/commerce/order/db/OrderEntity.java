package com.pk.commerce.order.db;

import org.springframework.data.annotation.Id;

public class OrderEntity {
    @Id
    private Long id;//db identity primary key

    private Long orderRef;//reference separately generated from sequence generator



}
