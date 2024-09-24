package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.model.CartItem;

public interface ICartItemService {
    void addItemToCart(int cartId, int productId, int quantity);
    void removeItemFromCart(int cartId, int productId);
    void updateItemQuantity(int cartId, int productId, int quantity);
    CartItem getCartItem(int cartId, int productId);
}
