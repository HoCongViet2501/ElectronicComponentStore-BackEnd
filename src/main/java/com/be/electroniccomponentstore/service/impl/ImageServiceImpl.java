package com.be.electroniccomponentstore.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.be.electroniccomponentstore.model.entity.Image;
import com.be.electroniccomponentstore.model.entity.Product;
import org.apache.maven.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.be.electroniccomponentstore.dto.ImageDTO;
import com.be.electroniccomponentstore.repository.ImageRepository;
import com.be.electroniccomponentstore.service.ImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class ImageServiceImpl implements ImageService {
    
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/electronic-component-store.appspot.com/o/%s?alt=media&token=%s";
    
    private final ImageRepository imageRepository;
    
    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    @Override
    public ImageDTO upload(MultipartFile multipartFile, Product product) throws InternalErrorException {
        try {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
            File file = this.convertToFile(multipartFile, fileName);
            String url = this.uploadFile(file, fileName);
            file.delete();
            
            Image image = new Image();
            image.setFileURL(url);
            image.setProduct(product);
            return ImageDTO.build(this.imageRepository.save(image));
            
        } catch (IOException | InternalErrorException e) {
            e.printStackTrace();
            throw new InternalErrorException("Error when upload file", e.getCause());
        }
    }
    
    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("electronic-component-store.appspot.com", fileName);
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType("media").build();
        Credentials credentials = GoogleCredentials
                .fromStream(Files.newInputStream(Paths.get("src/main/resources/static/credentials.json")));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8), URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
    
    private File convertToFile(MultipartFile multipartFile, String fileName) throws InternalErrorException {
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new InternalErrorException("Error converting multipartFile to file", e.getCause());
        }
        return file;
    }
    
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
