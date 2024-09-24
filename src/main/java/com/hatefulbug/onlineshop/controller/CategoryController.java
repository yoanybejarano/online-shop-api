package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.model.Category;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/main")
    public ResponseEntity<ApiResponse> getMainCategories() {
        List<Category> categories = categoryService.getMainCategories();
        return  ResponseEntity.ok(new ApiResponse("Get Main Success", categories));
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<ApiResponse> getSubCategories(@PathVariable int id){
        List<Category> subCategories = categoryService.getSubCategories(id);
        return  ResponseEntity.ok(new ApiResponse("Get Subcategories Success", subCategories));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable int id){
        Category category = categoryService.getCategoryById(id);
        return  ResponseEntity.ok(new ApiResponse("Get Category Success", category));
    }

}
