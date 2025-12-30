package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.api.Amount;
import com.pk.commerce.merchant.api.CurrencyCode;
import com.pk.commerce.merchant.api.item.Item;
import com.pk.commerce.merchant.api.item.ItemName;
import com.pk.commerce.merchant.api.item.ItemPrice;
import com.pk.commerce.merchant.api.item.ItemRef;
import com.pk.commerce.merchant.domain.item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    //view and filter items
    private final Item dummyItem = new Item(
            new ItemRef(123L),
            new ItemName("123"),
            new ItemPrice(new Amount(BigDecimal.ONE), CurrencyCode.RSD),
            null);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemRequest itemRequest) {
        itemService.add(itemRequest.name(), itemRequest.price().amount(), itemRequest.price().currency(), itemRequest.discount() != null ? itemRequest.discount().percent() : null, null);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editItem(@RequestBody ItemRequest itemRequest) {
        return ResponseEntity.ok(dummyItem);
    }

    @GetMapping("/{itemRef}")
    public ResponseEntity<?> getItem(@PathVariable("itemRef") String itemRef) {
        return ResponseEntity.ok(dummyItem);
    }

    @GetMapping
    public ResponseEntity<?> getItems(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        List<Item> items = itemService.findByName(name);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{itemRef}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemRef") String itemRef) {
        return ResponseEntity.ok(List.of(dummyItem));
    }

}
