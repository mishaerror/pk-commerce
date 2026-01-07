package com.pk.commerce.merchant.domain.merchant;

import com.pk.commerce.config.MerchantRequestContext;
import com.pk.commerce.merchant.api.category.Category;
import com.pk.commerce.merchant.api.category.CategoryName;
import com.pk.commerce.merchant.api.category.CategoryRef;
import com.pk.commerce.merchant.db.CategoryEntity;
import com.pk.commerce.merchant.db.CategoryRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Supplier;

@Service
public class CategoriesService {
    private final CategoryRepository repository;

    public CategoriesService(CategoryRepository repository) {
        this.repository = repository;
    }

    private static @NonNull Supplier<IllegalArgumentException> notFoundExceptionSupplier(String categoryRef) {
        return () -> new IllegalArgumentException(String.format("Category not found by ref: %s", categoryRef));
    }

    public String addCategory(String name) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setRef(repository.refSequence());
        categoryEntity.setMerchantRef(MerchantRequestContext.getMerchantRefLong());

        repository.save(categoryEntity);

        return categoryEntity.getRef().toString();
    }

    public void editCategory(Category category) {
        Long categoryRef = Long.valueOf(category.getRef().refId());
        CategoryEntity categoryEntity = repository.findByRef(categoryRef, MerchantRequestContext.getMerchantRefLong())
                .orElseThrow(notFoundExceptionSupplier(category.getRef().refId()));
        categoryEntity.setName(category.getName().toString());

        repository.save(categoryEntity);
    }

    public void deleteCategory(String ref) {
        CategoryEntity categoryEntity = repository.findByRef(Long.valueOf(ref), MerchantRequestContext.getMerchantRefLong()).orElseThrow(
                notFoundExceptionSupplier(ref)
        );

        repository.delete(categoryEntity);
    }

    public List<Category> findAllCategories(String name) {
        String nameLike = StringUtils.hasText(name) ? "%" + name.trim() + "%" : "%";

        return repository.findByName(nameLike, MerchantRequestContext.getMerchantRefLong()).stream().map(
                e -> new Category(new CategoryName(e.getName()), null, new CategoryRef(e.getRef().toString()))
        ).toList();
    }

    public Category findCategoryByRef(String ref) {

        CategoryEntity e = repository.findByRef(Long.valueOf(ref), MerchantRequestContext.getMerchantRefLong()).orElseThrow(
                notFoundExceptionSupplier(ref)
        );

        return new Category(new CategoryName(e.getName()), null, new CategoryRef(ref));
    }

}
