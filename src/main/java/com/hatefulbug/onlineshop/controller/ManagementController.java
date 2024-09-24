package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.dto.ProductDto;
import com.hatefulbug.onlineshop.model.Product;
import com.hatefulbug.onlineshop.request.AddProductRequest;
import com.hatefulbug.onlineshop.request.ProductUpdateRequest;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/management")
@Tag(name = "Management")
public class ManagementController {

    private final IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        Product theProduct = productService.addProduct(product);
        ProductDto productDto = productService.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Add Product Success", productDto));
    }

    @PutMapping("/product/{productId}/update")
    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable int productId) {
        Product theProduct = productService.updateProduct(request, productId);
        ProductDto productDto = productService.convertToDto(theProduct);
        return ResponseEntity.ok(new ApiResponse("Update Product Success", productDto));
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Delete Product Success", productId));
    }

}
