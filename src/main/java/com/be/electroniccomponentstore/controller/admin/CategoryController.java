package com.be.electroniccomponentstore.controller.admin;

import com.be.electroniccomponentstore.dto.request.CategoryRequest;
import com.be.electroniccomponentstore.dto.response.CategoryResponse;
import com.be.electroniccomponentstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    @Operation(summary = "get list categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "get list successfully!")
    })
    public ResponseEntity<Object> getListCategories() {
        List<CategoryResponse> responseList = this.categoryService.getListCategories();
        return ResponseEntity.ok().body(responseList);
    }
    
    @PostMapping
    @Operation(summary = "create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create category successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = this.categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok().body(categoryResponse);
    }
    
    @PutMapping("/{categoryId}")
    @Operation(summary = "update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "update successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
        CategoryResponse categoryResponse = this.categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok().body(categoryResponse);
    }
    
    @DeleteMapping("/{categoryId}")
    @Operation(summary = "delete category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "delete successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> deleteBrand(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
