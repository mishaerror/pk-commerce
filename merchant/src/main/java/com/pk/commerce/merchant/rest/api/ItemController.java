package com.pk.commerce.merchant.rest.api;

import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    //view and filter items

    @PostMapping
    public ResponseEntity<?> addItem() {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> editItem() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{itemRef}")
    public ResponseEntity<?> getItem(@PathVariable("itemRef") String itemRef) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<?> getItems(@RequestParam(name = "name", required = false) String name) {
        return ResponseEntity.ok().build();
    }

}
