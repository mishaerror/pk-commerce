package com.pk.commerce.orders.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT nextval('order_ref_seq')")
    Long refSequence();

    @Query("SELECT * from orders o where o.merchant_ref = :merchantRef order by order_time desc")
    List<OrderEntity> findOrdersForMerchant(@Param("merchantRef") Long merchantRef);

    @Query("SELECT * from orders o where o.merchant_ref = :merchantRef and o.order_time >= :orderTimeFrom " +
            " and o.order_time <= :orderTimeTo " +
            " and o.state like :orderState" +
            " order by o.order_time desc")
    List<OrderEntity> findOrdersForMerchantAndTime(@Param("merchantRef") Long merchantRef,
                                                   @Param("orderTimeFrom") Timestamp orderTimeFrom,
                                                   @Param("orderTimeTo") Timestamp orderTimeTo,
                                                   @Param("orderState") String state);

    @Query("SELECT * from orders o where o.ref = :orderRef and o.merchant_ref = :merchantRef order by order_time desc")
    Optional<OrderEntity> findOrderByRefAndMerchant(@Param("orderRef") Long orderRef, @Param("merchantRef") Long merchantRef);

    @Query("SELECT * from orders o where o.ref = :orderRef")
    Optional<OrderEntity> findOrderByRef(@Param("orderRef") Long orderRef);
}
