package com.be.electroniccomponentstore.service;

import com.be.electroniccomponentstore.dto.ImageDTO;
import com.be.electroniccomponentstore.model.entity.Product;
import org.apache.maven.InternalErrorException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDTO upload(MultipartFile multipartFile, Product product) throws InternalErrorException;
    
}
