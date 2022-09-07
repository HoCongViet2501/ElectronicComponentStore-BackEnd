package com.be.electroniccomponentstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {
    
    private String name;
    
    private String description;
    
    private String phoneNumber;
    
    private String address;
}
