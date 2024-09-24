package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.CartDto;
import com.hatefulbug.onlineshop.model.Cart;
import com.hatefulbug.onlineshop.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(int id);
    void clearCart(int id);
    Cart getCartByUserId(int userId);
    BigDecimal getTotalPrice(int id);
    Cart initializeNewCart(User user);
    CartDto getCartDto(int id);
    CartDto getCartDtoByUserId(int userId);
}
