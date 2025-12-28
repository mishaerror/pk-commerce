package com.pk.commerce.merchant.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    @Query("SELECT nextval('category_ref_seq')")
    Long refSequence();

    @Query("select * from categories c where c.ref = :ref and c.merchant_ref = :merchantRef")
    Optional<CategoryEntity> findByRef(Long ref, Long merchantRef);

    @Query("select * from categories c where lower(c.name) like lower(:nameLike) and c.merchant_ref = :merchantRef")
    List<CategoryEntity> findByName(String nameLike, Long merchantRef);
}
