package com.hatefulbug.onlineshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CartItemDto {
    private Integer id;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Instant createdAt;
}
