package com.hatefulbug.onlineshop.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private UserDto user;
    private Integer rating;
    private String comment;
}
