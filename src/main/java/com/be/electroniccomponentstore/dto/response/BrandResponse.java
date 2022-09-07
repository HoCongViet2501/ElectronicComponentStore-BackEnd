package com.be.electroniccomponentstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private Long id;
    
    private String name;
    
    private String description;
    
    private String phoneNumber;
    
    private String address;
}
