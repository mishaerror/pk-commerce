package com.pk.commerce.merchant.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository  extends CrudRepository<MerchantEntity, Long> {
    @Query("SELECT nextval('merchant_ref_seq')")
    Long refSequence();

    Optional<MerchantEntity> findByRef(Long ref);
    Optional<MerchantEntity> findByName(String name);
    Optional<MerchantEntity> findByEmail(String email);
    Optional<MerchantEntity> findByShopName(String name);
}
