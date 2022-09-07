package com.be.electroniccomponentstore.service.impl;

import com.be.electroniccomponentstore.dto.request.BrandRequest;
import com.be.electroniccomponentstore.dto.response.BrandResponse;
import com.be.electroniccomponentstore.exceptions.ResourceNotFoundException;
import com.be.electroniccomponentstore.mapping.MapData;
import com.be.electroniccomponentstore.model.entity.Brand;
import com.be.electroniccomponentstore.repository.BrandRepository;
import com.be.electroniccomponentstore.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    
    private final BrandRepository brandRepository;
    
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    
    @Override
    public List<BrandResponse> getListBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        return MapData.mapList(brands, BrandResponse.class);
    }
    
    @Override
    public BrandResponse createBrand(BrandRequest request) {
        Brand brand = MapData.mapOne(request, Brand.class);
        this.brandRepository.save(brand);
        return MapData.mapOne(brand, BrandResponse.class);
    }
    
    @Override
    public BrandResponse updateBrand(Long brandId, BrandRequest request) {
        Brand brand = this.brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException("not found brand"));
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setAddress(request.getAddress());
        brand.setPhoneNumber(request.getPhoneNumber());
        Brand savedBrand = this.brandRepository.save(brand);
        return MapData.mapOne(savedBrand, BrandResponse.class);
    }
    
    @Override
    public void deleteBrand(Long brandId) {
        Brand brand = this.brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException("not found brand"));
        this.brandRepository.delete(brand);
    }
}
