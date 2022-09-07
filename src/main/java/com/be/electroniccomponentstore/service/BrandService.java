package com.be.electroniccomponentstore.service;

import com.be.electroniccomponentstore.dto.request.BrandRequest;
import com.be.electroniccomponentstore.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getListBrands();
    
    BrandResponse createBrand(BrandRequest request);
    
    BrandResponse updateBrand(Long id,BrandRequest request);
    
    void deleteBrand(Long brandId);
}
