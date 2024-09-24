package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.dto.ProductDto;
import com.hatefulbug.onlineshop.exception.AlreadyExistsException;
import com.hatefulbug.onlineshop.exception.ResourceNotFoundException;
import com.hatefulbug.onlineshop.model.Category;
import com.hatefulbug.onlineshop.model.Product;
import com.hatefulbug.onlineshop.repository.ProductRepository;
import com.hatefulbug.onlineshop.request.AddProductRequest;
import com.hatefulbug.onlineshop.request.ProductUpdateRequest;
import com.hatefulbug.onlineshop.response.PageResponse;
import com.hatefulbug.onlineshop.service.ICategoryService;
import com.hatefulbug.onlineshop.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ICategoryService categoryService;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(AddProductRequest request) {
        if (productExists(request.getName())){
            throw new AlreadyExistsException(String.format("%s already exists, you may update this product instead!", request.getName()));
        }
        Category category = categoryService.getCategoryById(request.getCategoryId());
        return productRepository.save(createProduct(request, category));
    }

    private boolean productExists(String name) {
        return productRepository.existsByName(name);
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {throw new ResourceNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, int productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct,request))
                .map(productRepository :: save)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStock(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        Category category = categoryService.getCategoryById(request.getCategory().getId());
        existingProduct.setCategory(category);
        return  existingProduct;
    }

    @Override
    public PageResponse<ProductDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDto> productResponse = productPage.stream().map(this::convertToDto).toList();
        return new PageResponse<>(
                productResponse,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast()
        );
    }

    @Override
    public PageResponse<ProductDto> getProductsByCategory(int page, int size, int categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCategoryId(pageable, categoryId);
        List<ProductDto> productResponse = productPage.stream().map(this::convertToDto).toList();
        return new PageResponse<>(
                productResponse,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast()
        );
    }

    @Override
    public PageResponse<ProductDto> getProductsByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByName(pageable, name);
        List<ProductDto> productResponse = productPage.stream().map(this::convertToDto).toList();
        return new PageResponse<>(
                productResponse,
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast()
        );
    }

    @Override
    public int countProductsByCategory(int categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

}
