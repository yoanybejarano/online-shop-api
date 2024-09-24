package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    void deleteAllByCartId(int id);
}
