package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.exception.ResourceNotFoundException;
import com.hatefulbug.onlineshop.model.Category;
import com.hatefulbug.onlineshop.repository.CategoryRepository;
import com.hatefulbug.onlineshop.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getMainCategories() {
        return categoryRepository.getMainCategories();
    }

    @Override
    public List<Category> getSubCategories(int id) {
        return categoryRepository.findByParentCategoryId(id);
    }


}
