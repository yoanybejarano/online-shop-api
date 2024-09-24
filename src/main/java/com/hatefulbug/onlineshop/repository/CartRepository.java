package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(int userId);
}
