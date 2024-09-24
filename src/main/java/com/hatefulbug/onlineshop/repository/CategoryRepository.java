package com.hatefulbug.onlineshop.repository;

import com.hatefulbug.onlineshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM categories WHERE parent_category_id IS NULL", nativeQuery = true)
    List<Category> getMainCategories();
    List<Category> findByParentCategoryId(int parentCategoryId);

}
