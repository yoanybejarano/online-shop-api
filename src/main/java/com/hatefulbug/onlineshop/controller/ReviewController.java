package com.hatefulbug.onlineshop.controller;

import com.hatefulbug.onlineshop.dto.ReviewDto;
import com.hatefulbug.onlineshop.request.AddReviewRequest;
import com.hatefulbug.onlineshop.request.UpdateReviewRequest;
import com.hatefulbug.onlineshop.response.ApiResponse;
import com.hatefulbug.onlineshop.service.IReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/reviews")
@Tag(name = "Review")
public class ReviewController {
    private final IReviewService reviewService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> getReviews(@PathVariable int productId) {
        List<ReviewDto> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(new ApiResponse("Get Reviews Success", reviews));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addReview(@RequestBody AddReviewRequest request) {
        ReviewDto review = reviewService.addReview(request);
        return ResponseEntity.ok(new ApiResponse("Save Review Success", review));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> getCart(@RequestBody UpdateReviewRequest request) {
        ReviewDto review = reviewService.updateReview(request);
        return ResponseEntity.ok(new ApiResponse("Edit Review Success", review));
    }

}
