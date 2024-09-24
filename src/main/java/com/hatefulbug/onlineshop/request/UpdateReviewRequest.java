package com.hatefulbug.onlineshop.request;

import lombok.Data;

@Data
public class UpdateReviewRequest {
    private int reviewId;
    private Integer rating;
    private String comment;
}
