package com.hatefulbug.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDto category;
    private Integer stock;
    private BigDecimal rating;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<ReviewDto> reviews;
    private List<ImageDto> images;
}