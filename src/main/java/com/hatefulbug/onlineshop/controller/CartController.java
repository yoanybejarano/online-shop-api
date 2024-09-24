package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.dto.CartDto;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.ICartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carts")
@Tag(name = "Cart")
public class CartController {
    private final ICartService cartService;

    @GetMapping("/my-cart/{cartId}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable int cartId) {
        CartDto cart = cartService.getCartDto(cartId);
        return ResponseEntity.ok(new ApiResponse("Get Cart Success", cart));
    }

    @GetMapping("/total-price/{cartId}")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable int cartId) {
        BigDecimal totalPrice = cartService.getTotalPrice(cartId);
        return ResponseEntity.ok(new ApiResponse("Get Total Amount Success", totalPrice));
    }

    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<ApiResponse> clearCart( @PathVariable int cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Clear Cart Success", null));
    }

}
