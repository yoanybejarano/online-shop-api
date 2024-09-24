package com.hatefulbug.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;
    @JsonIgnore
    private UserDto user;
    private BigDecimal totalAmount;
    private OrderStatusDto status;
    private PaymentMethodDto paymentMethod;
    private String shippingAddress;
    private Instant createdAt;
    private Set<OrderItemDto> orderItems;
}
