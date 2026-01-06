package com.pk.commerce.orders.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT nextval('order_ref_seq')")
    Long refSequence();

    @Query("SELECT * from orders o where o.merchant_ref = :merchantRef")
    List<OrderEntity> findOrdersForMerchant(@Param("merchantRef") Long merchantRef);

    @Query("SELECT * from orders o where o.ref = :orderRef and o.merchant_ref = :merchantRef")
    Optional<OrderEntity> findOrderByRefAndMerchant(@Param("orderRef") Long orderRef, @Param("merchantRef") Long merchantRef);

    @Query("SELECT * from orders o where o.ref = :orderRef")
    Optional<OrderEntity> findOrderByRef(@Param("orderRef") Long orderRef);
}
