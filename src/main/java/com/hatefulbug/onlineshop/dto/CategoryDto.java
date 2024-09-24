package com.hatefulbug.onlineshop.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private CategoryDto parentCategory;
}
