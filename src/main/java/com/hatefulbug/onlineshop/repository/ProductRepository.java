package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByCategoryId(Pageable pageable, int categoryId);
    Page<Product> findByName(Pageable pageable,String name);
    Boolean existsByName(String name);
    int countByCategoryId(int categoryId);

}
