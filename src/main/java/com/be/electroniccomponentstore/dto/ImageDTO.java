package com.be.electroniccomponentstore.dto;

import com.be.electroniccomponentstore.model.entity.Image;
import com.be.electroniccomponentstore.model.entity.Product;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    private Long id;
    
    @NotNull(message = "required")
    private String fileURL;
    
    public static ImageDTO build(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .fileURL(image.getFileURL())
                .build();
    }
}
