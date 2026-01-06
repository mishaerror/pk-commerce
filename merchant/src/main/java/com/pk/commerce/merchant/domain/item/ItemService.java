package com.pk.commerce.merchant.domain.item;


import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;
import com.pk.commerce.merchant.api.item.ItemDiscount;
import com.pk.commerce.merchant.api.item.ItemName;
import com.pk.commerce.merchant.api.item.ItemPrice;
import com.pk.commerce.merchant.api.item.ItemRef;
import com.pk.commerce.merchant.api.merchant.MerchantRef;
import com.pk.commerce.merchant.api.merchant.MerchantStatus;
import com.pk.commerce.config.MerchantRequestContext;
import com.pk.commerce.merchant.db.ItemEntity;
import com.pk.commerce.merchant.db.ItemRepository;
import com.pk.commerce.merchant.db.MerchantRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository, MerchantRepository merchantRepository) {
        this.repository = repository;
    }

    private final Function<ItemEntity, com.pk.commerce.merchant.api.item.Item> entityToDto = entity -> {
        ItemDiscount discount = entity.getDiscount() != null ? new ItemDiscount(entity.getDiscount()) : null;
        return new com.pk.commerce.merchant.api.item.Item(
                new ItemRef(entity.getRef()),
                new ItemName(entity.getName()),
                entity.getCount(),
                new ItemPrice(new Amount(entity.getAmount()), CurrencyCode.valueOf(entity.getCurrency())),
                discount,
                null,
                new MerchantRef(entity.getMerchantRef()),
                MerchantStatus.valueOf(entity.getMerchantStatus())
        );
    };

    private static @NonNull Supplier<IllegalArgumentException> notFoundExceptionSupplier(Long ref) {
        return () -> new IllegalArgumentException(String.format("Item not found by ref: %s", ref));
    }

    public void edit(Long ref, String name, BigDecimal amount, String currency, BigDecimal discount, Integer count, String image) {
        if (image != null) {
            //process and validate image
        }

        ItemEntity entity = repository.findByRef(ref, MerchantRequestContext.getMerchantRefLong()).orElseThrow(notFoundExceptionSupplier(ref));
        entity.setName(name);
        entity.setAmount(amount);
        entity.setCurrency(currency);
        entity.setDiscount(discount);

        entity.setRef(repository.refSequence());
        entity.setMerchantRef(MerchantRequestContext.getMerchantRefLong());
        entity.setCount(count);

        repository.save(entity);
    }

    public void add(String name, BigDecimal amount, String currency, BigDecimal discount, Integer count, String image) {
        if (image != null) {
            //process and validate image
        }

        ItemEntity entity = new ItemEntity();
        entity.setName(name);
        entity.setAmount(amount);
        entity.setCurrency(currency);
        entity.setDiscount(discount);
        entity.setRef(repository.refSequence());
        entity.setMerchantRef(MerchantRequestContext.getMerchantRefLong());
        entity.setCount(count);

        repository.save(entity);
    }

    public List<com.pk.commerce.merchant.api.item.Item> findByName(String name) {
        String nameLike = StringUtils.hasText(name) ? "%" + name.trim() + "%" : "%";

        List<ItemEntity> entities = repository.findByName(nameLike, MerchantRequestContext.getMerchantRefLong());

        return entities.stream().map(entityToDto).toList();
    }

    public void delete(Long itemRef) {
        //TODO checks for usage
        ItemEntity itemEntity = repository.findByRef(itemRef, MerchantRequestContext.getMerchantRefLong()).orElseThrow(notFoundExceptionSupplier(itemRef));
        repository.delete(itemEntity);
    }

    public com.pk.commerce.merchant.api.item.Item findByRef(Long itemRef) {
        ItemEntity itemEntity = repository.findByRef(itemRef).orElseThrow(notFoundExceptionSupplier(itemRef));

        return entityToDto.apply(itemEntity);
    }
}
