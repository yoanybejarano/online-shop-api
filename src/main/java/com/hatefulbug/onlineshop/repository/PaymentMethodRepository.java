package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
