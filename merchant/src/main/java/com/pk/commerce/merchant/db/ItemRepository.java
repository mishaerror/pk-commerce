package com.pk.commerce.merchant.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    @Query("SELECT nextval('item_ref_seq')")
    Long refSequence();

    @Query("select c.*, m.status as merchant_status from items c join merchants m on m.ref=c.merchant_ref where c.ref = :ref and c.merchant_ref = :merchantRef")
    Optional<ItemEntity> findByRef(Long ref, Long merchantRef);

    @Query("select c.*, m.status as merchant_status from items c join merchants m on m.ref=c.merchant_ref where c.ref = :ref")
    Optional<ItemEntity> findByRef(Long ref);

    @Query("select c.*, m.status as merchant_status from items c join merchants m on m.ref=c.merchant_ref where lower(c.name) like lower(:nameLike) and c.merchant_ref = :merchantRef")
    List<ItemEntity> findByName(String nameLike, Long merchantRef);
}
