package com.be.electroniccomponentstore.model.entity;

import com.be.electroniccomponentstore.dto.request.BrandRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String address;
    
    @JsonIgnore
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    
    public Brand( String name, String description, String phoneNumber, String address) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public static Brand buildFromRequest(BrandRequest request) {
        return new Brand(request.getName(),request.getDescription(), request.getPhoneNumber(), request.getAddress());
    }
    
}
