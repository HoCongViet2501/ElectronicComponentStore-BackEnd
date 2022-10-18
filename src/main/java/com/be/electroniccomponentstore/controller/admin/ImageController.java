package com.be.electroniccomponentstore.controller.admin;

import com.be.electroniccomponentstore.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@Slf4j
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file) throws InternalErrorException {
        log.info("upload image:  File Name : {}", file.getOriginalFilename());
        return ResponseEntity.ok().body(imageService.upload(file));
    }
    
/*    @PostMapping("/download")
    public ResponseEntity<Object> downloadImage(@RequestParam("file") String file) {
        log.info("download image:  File Name : {}", file);
        return ResponseEntity.ok().body(imageService.download(file));
    }*/
}
