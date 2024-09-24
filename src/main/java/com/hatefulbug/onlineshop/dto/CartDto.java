package com.hatefulbug.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class CartDto {
    private Integer id;
    @JsonIgnore
    private UserDto user;
    private BigDecimal totalAmount;
    private String sessionId;
    private Instant createdAt;
    private Set<CartItemDto> items;
}
