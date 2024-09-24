package com.hatefulbug.onlineshop.request;

import lombok.Data;

@Data
public class AddReviewRequest {
    private int userId;
    private int productId;
    private Integer rating;
    private String comment;
}
