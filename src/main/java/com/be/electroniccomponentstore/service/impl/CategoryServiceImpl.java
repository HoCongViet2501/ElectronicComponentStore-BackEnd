package com.be.electroniccomponentstore.service.impl;

import com.be.electroniccomponentstore.dto.request.CategoryRequest;
import com.be.electroniccomponentstore.dto.response.CategoryResponse;
import com.be.electroniccomponentstore.exceptions.ResourceNotFoundException;
import com.be.electroniccomponentstore.mapping.MapData;
import com.be.electroniccomponentstore.model.entity.Category;
import com.be.electroniccomponentstore.repository.CategoryRepository;
import com.be.electroniccomponentstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public List<CategoryResponse> getListCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return MapData.mapList(categories, CategoryResponse.class);
    }
    
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = MapData.mapOne(request, Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        return MapData.mapOne(savedCategory, CategoryResponse.class);
    }
    
    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = this.categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found category have id " + id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return MapData.mapOne(category, CategoryResponse.class);
    }
    
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("not found category have id " + categoryId));
        this.categoryRepository.delete(category);
    }
}
