package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.merchant.api.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    @PostMapping
    ResponseEntity<?> upload(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(new RestResponse("uplaoded", "image uploaded", Optional.of(12234)));
    }
}
