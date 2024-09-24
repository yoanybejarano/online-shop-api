package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.dto.OrderDto;
import com.hatefulbug.onlineshop.model.Order;
import com.hatefulbug.onlineshop.request.PlaceOrderRequest;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.IOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
@Tag(name = "Order")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody PlaceOrderRequest request) {
        Order order =  orderService.placeOrder(request);
        OrderDto orderDto =  orderService.convertToDto(order);
        return ResponseEntity.ok(new ApiResponse("Create Order Success", orderDto));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable int orderId) {
        OrderDto order = orderService.getOrder(orderId);
        return ResponseEntity.ok(new ApiResponse("Get Order Success", order));
    }

    @GetMapping("/user/{userId}/order")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable int userId) {
        List<OrderDto> order = orderService.getUserOrders(userId);
        return ResponseEntity.ok(new ApiResponse("Get User Orders Success", order));
    }
}
