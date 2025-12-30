package com.pk.commerce.merchant.domain.item;

import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;
import com.pk.commerce.merchant.api.item.Item;
import com.pk.commerce.merchant.api.item.ItemName;
import com.pk.commerce.merchant.api.item.ItemPrice;
import com.pk.commerce.merchant.api.item.ItemRef;
import com.pk.commerce.merchant.config.MerchantRequestContext;
import com.pk.commerce.merchant.db.ItemEntity;
import com.pk.commerce.merchant.db.ItemRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    private static @NonNull Supplier<IllegalArgumentException> notFoundExceptionSupplier(String ref) {
        return () -> new IllegalArgumentException(String.format("Item not found by ref: %s", ref));
    }

    public void add(String name, BigDecimal amount, String currency, BigDecimal discount, String image) {
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

        repository.save(entity);
    }

    public List<Item> findByName(String name) {
        String nameLike = StringUtils.hasText(name) ? "%" + name.trim() + "%" : "%";

        List<ItemEntity> entities = repository.findByName(nameLike, MerchantRequestContext.getMerchantRefLong());
        return entities.stream().map(
                i ->
                        new Item(new ItemRef(
                                i.getRef()),
                                new ItemName(i.getName()),
                                new ItemPrice(new Amount(i.getAmount()), CurrencyCode.valueOf(i.getCurrency())),
                                null)).toList();

    }
}
