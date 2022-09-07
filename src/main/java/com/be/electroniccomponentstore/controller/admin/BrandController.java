package com.be.electroniccomponentstore.controller.admin;

import com.be.electroniccomponentstore.dto.request.BrandRequest;
import com.be.electroniccomponentstore.dto.response.BrandResponse;
import com.be.electroniccomponentstore.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    
    private final BrandService brandService;
    
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    
    @GetMapping
    public ResponseEntity<Object> getListBrand() {
        List<BrandResponse> responseList = this.brandService.getListBrands();
        return ResponseEntity.ok().body(responseList);
    }
    
    @PostMapping
    @Operation(summary = "create new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create brand successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        BrandResponse brandResponse = this.brandService.createBrand(brandRequest);
        return ResponseEntity.ok().body(brandResponse);
    }
    
    @PutMapping("/{brandId}")
    @Operation(summary = "update brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "update successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> updateBrand(@PathVariable Long brandId, @Valid @RequestBody BrandRequest request) {
        BrandResponse brandResponse = this.brandService.updateBrand(brandId, request);
        return ResponseEntity.ok().body(brandResponse);
    }
    
    @DeleteMapping("/{brandId}")
    @Operation(summary = "delete brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "delete successfully!"),
            @ApiResponse(responseCode = "500", description = "something wrong!"),
            @ApiResponse(responseCode = "400", description = "bad request!")
    })
    public ResponseEntity<Object> deleteBrand(@PathVariable Long brandId) {
        this.brandService.deleteBrand(brandId);
        return ResponseEntity.ok().build();
    }
}
