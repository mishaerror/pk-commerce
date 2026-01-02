package com.pk.commerce.orders.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT nextval('order_ref_seq')")
    Long refSequence();
}
