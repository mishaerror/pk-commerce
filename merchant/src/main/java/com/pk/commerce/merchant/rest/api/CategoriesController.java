package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.api.merchant.category.Category;
import com.pk.commerce.api.merchant.category.CategoryName;
import com.pk.commerce.api.merchant.category.CategoryRef;
import com.pk.commerce.merchant.domain.merchant.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryRequest request) {
        String ref = categoriesService.addCategory(request.name());
        return ResponseEntity.created(URI.create("/api/categories/" + ref)).build();
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody CategoryRequest request) {
        categoriesService.editCategory(new Category(new CategoryName(request.name()), null, new CategoryRef(request.ref())));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ref}")
    public ResponseEntity<?> get(@PathVariable("ref") String categoryRef) {
        Category category = categoriesService.findCategoryByRef(categoryRef);
        return ResponseEntity.ok(category);
    }

    @GetMapping()
    public ResponseEntity<?> getAllByName(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(categoriesService.findAllCategories(name));
    }

    @DeleteMapping("/{ref}")
    public ResponseEntity<?> delete(@PathVariable("ref") String ref) {
        categoriesService.deleteCategory(ref);

        return ResponseEntity.ok().build();
    }
}
