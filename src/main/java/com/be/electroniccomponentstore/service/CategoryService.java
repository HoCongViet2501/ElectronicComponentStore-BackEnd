package com.be.electroniccomponentstore.service;

import com.be.electroniccomponentstore.dto.request.BrandRequest;
import com.be.electroniccomponentstore.dto.request.CategoryRequest;
import com.be.electroniccomponentstore.dto.response.BrandResponse;
import com.be.electroniccomponentstore.dto.response.CategoryResponse;

import java.util.List;

public interface  CategoryService {
    List<CategoryResponse> getListCategories();
    
    CategoryResponse createCategory(CategoryRequest request);
    
    CategoryResponse updateCategory(Long id,CategoryRequest request);
    
    void deleteCategory(Long categoryId);
}
