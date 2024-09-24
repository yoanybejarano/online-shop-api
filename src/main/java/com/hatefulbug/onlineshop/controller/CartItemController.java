package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.model.Cart;
import com.hatefulbug.onlineshop.model.User;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.ICartItemService;
import com.hatefulbug.onlineshop.service.ICartService;
import com.hatefulbug.onlineshop.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
@Tag(name = "CartItem")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam Integer cartId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartId == null ? cartService.initializeNewCart(user) : cartService.getCart(cartId);
        cartItemService.addItemToCart(cart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable int cartId,
                                                           @PathVariable int itemId,
                                                           @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable int cartId, @PathVariable int itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
    }

}