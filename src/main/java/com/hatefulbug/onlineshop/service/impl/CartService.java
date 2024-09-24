package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.dto.CartDto;
import com.hatefulbug.onlineshop.exception.ResourceNotFoundException;
import com.hatefulbug.onlineshop.model.Cart;
import com.hatefulbug.onlineshop.model.User;
import com.hatefulbug.onlineshop.repository.CartItemRepository;
import com.hatefulbug.onlineshop.repository.CartRepository;
import com.hatefulbug.onlineshop.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Cart getCart(int id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    @Override
    public Cart initializeNewCart(User user) {
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public CartDto getCartDto(int id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return convertToDto(cart);
    }

    @Override
    public CartDto getCartDtoByUserId(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return convertToDto(cart);
    }

    @Transactional
    @Override
    public void clearCart(int id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.clearCart();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(int id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    private CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

}
