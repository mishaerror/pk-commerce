package com.pk.commerce.merchant.rest.api;

import com.pk.commerce.api.merchant.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    @PostMapping
    ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {
        byte[] imageBytes = file.getInputStream().readAllBytes();
        return ResponseEntity.ok(new RestResponse("uploaded", "image uploaded", Optional.of(12234)));
    }

    @GetMapping("/{imageRef}")
    ResponseEntity<?> getImageByRef(@RequestParam("imageRef") Long imageRef) {
        //TODO
        return ResponseEntity.ok().build();
    }
}
