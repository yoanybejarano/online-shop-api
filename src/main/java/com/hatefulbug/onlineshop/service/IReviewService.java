package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.ReviewDto;
import com.hatefulbug.onlineshop.model.Review;
import com.hatefulbug.onlineshop.request.AddReviewRequest;
import com.hatefulbug.onlineshop.request.UpdateReviewRequest;

import java.util.List;

public interface IReviewService {
    List<ReviewDto> getReviewsByProductId(int productId);
    ReviewDto addReview(AddReviewRequest request);
    ReviewDto updateReview(UpdateReviewRequest request);
}
