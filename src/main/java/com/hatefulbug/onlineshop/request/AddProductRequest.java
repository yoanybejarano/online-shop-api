package com.hatefulbug.onlineshop.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String images;
    private int categoryId;
}
