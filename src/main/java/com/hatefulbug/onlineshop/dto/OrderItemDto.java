package com.hatefulbug.onlineshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Integer id;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
}