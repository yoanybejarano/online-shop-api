package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
    OrderStatus findByStatusName(String name);
}
