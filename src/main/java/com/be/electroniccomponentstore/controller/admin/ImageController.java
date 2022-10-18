package com.be.electroniccomponentstore.controller.admin;

import com.be.electroniccomponentstore.model.entity.Product;
import com.be.electroniccomponentstore.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@Slf4j
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam Long productId) throws InternalErrorException {
        log.info("upload image:  File Name : {}", file.getOriginalFilename());
        return ResponseEntity.ok().body(imageService.upload(file, productId));
    }
    
}
