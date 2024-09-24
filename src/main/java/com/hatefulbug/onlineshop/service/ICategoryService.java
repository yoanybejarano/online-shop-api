package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(int id);
    List<Category> getMainCategories();
    List<Category> getSubCategories(int id);
}
