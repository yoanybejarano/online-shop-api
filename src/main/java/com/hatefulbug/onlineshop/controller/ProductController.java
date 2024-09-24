package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.dto.ProductDto;
import com.hatefulbug.onlineshop.model.Product;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.response.PageResponse;
import com.hatefulbug.onlineshop.service.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
@Tag(name = "Product")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        PageResponse<ProductDto> products = productService.getAllProducts(page, size);
        return  ResponseEntity.ok(new ApiResponse("Get Products Success", products));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        ProductDto productDto = productService.convertToDto(product);
        return  ResponseEntity.ok(new ApiResponse("Get Product Success", productDto));
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<ApiResponse> getProductByName(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable String name){
        PageResponse<ProductDto> products = productService.getProductsByName(page, size, name);
        return  ResponseEntity.ok(new ApiResponse("Get Product Success", products));
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<ApiResponse> findProductByCategory(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @PathVariable int categoryId) {
        PageResponse<ProductDto> products = productService.getProductsByCategory(page, size, categoryId);
        return  ResponseEntity.ok(new ApiResponse("Get Products Success", products));
    }

}