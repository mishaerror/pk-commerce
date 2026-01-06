package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.api.item.Item;
import com.pk.commerce.merchant.domain.item.ItemService;
import com.pk.commerce.merchant.utils.ShortUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private static final String SHORTURL_BASE_PATH = "https://pk-commerce.co/shop/";
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemRequest itemRequest) {
        BigDecimal discount = null;
        if (StringUtils.hasText(itemRequest.discount())) {
            discount = new BigDecimal(itemRequest.discount(), new MathContext(5, RoundingMode.CEILING)).setScale(2, RoundingMode.CEILING);
        }
        itemService.add(itemRequest.name(), itemRequest.price().amount(), itemRequest.price().currency(), discount, itemRequest.count(), null);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editItem(@RequestBody ItemRequest itemRequest) {
        BigDecimal discount = null;
        if (StringUtils.hasText(itemRequest.discount())) {
            discount = new BigDecimal(itemRequest.discount(), new MathContext(5, RoundingMode.CEILING)).setScale(2, RoundingMode.CEILING);
        }
        itemService.edit(itemRequest.ref(), itemRequest.name(), itemRequest.price().amount(), itemRequest.price().currency(), discount, itemRequest.count(), null);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{itemRef}")
    public ResponseEntity<?> getItem(@PathVariable("itemRef") String itemRef) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getItems(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        List<Item> items = itemService.findByName(name);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{itemRef}")
    public ResponseEntity<?> deleteItem(@PathVariable("itemRef") Long itemRef) {
        itemService.delete(itemRef);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/short/{ref}")
    public ResponseEntity<?> shortUrlForItem(@PathVariable("ref") Long ref) {
        String url = SHORTURL_BASE_PATH + ShortUrl.encode(BigInteger.valueOf(ref));

        return ResponseEntity.ok(url);
    }

}
