package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(int productId);

}
