package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.ProductDto;
import com.hatefulbug.onlineshop.model.Product;
import com.hatefulbug.onlineshop.request.AddProductRequest;
import com.hatefulbug.onlineshop.request.ProductUpdateRequest;
import com.hatefulbug.onlineshop.response.PageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(int id);
    void deleteProductById(int id);
    Product updateProduct(ProductUpdateRequest product, int productId);
    PageResponse<ProductDto> getAllProducts(int page, int size);
    PageResponse<ProductDto> getProductsByCategory(int page, int size, int categoryId);
    PageResponse<ProductDto> getProductsByName(int page, int size, String name);
    int countProductsByCategory(int categoryId);
    ProductDto convertToDto(Product product);
}
