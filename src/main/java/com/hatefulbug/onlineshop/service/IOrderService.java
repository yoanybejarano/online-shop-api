package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.OrderDto;
import com.hatefulbug.onlineshop.model.Order;
import com.hatefulbug.onlineshop.request.PlaceOrderRequest;

import java.util.List;

public interface IOrderService {
    Order placeOrder(PlaceOrderRequest request);
    OrderDto getOrder(int orderId);
    List<OrderDto> getUserOrders(int userId);
    OrderDto convertToDto(Order order);
}
