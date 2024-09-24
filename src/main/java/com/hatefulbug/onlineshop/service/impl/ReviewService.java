package com.hatefulbug.onlineshop.service.impl;

import com.hatefulbug.onlineshop.dto.ReviewDto;
import com.hatefulbug.onlineshop.exception.ResourceNotFoundException;
import com.hatefulbug.onlineshop.model.Product;
import com.hatefulbug.onlineshop.model.Review;
import com.hatefulbug.onlineshop.model.User;
import com.hatefulbug.onlineshop.repository.ReviewRepository;
import com.hatefulbug.onlineshop.request.AddReviewRequest;
import com.hatefulbug.onlineshop.request.UpdateReviewRequest;
import com.hatefulbug.onlineshop.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getReviewsByProductId(int productId) {
        return reviewRepository.findByProductId(productId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ReviewDto addReview(AddReviewRequest request) {
        Product product = productService.getProductById(request.getProductId());
        User user = userService.getUserById(request.getUserId());
        Review review = Review.builder()
                .product(product)
                .user(user)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();
        Review result = reviewRepository.save(review);
        return convertToDto(result);
    }

    @Override
    public ReviewDto updateReview(UpdateReviewRequest request) {
        Review review = reviewRepository.findById(request.getReviewId()).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        Review result = reviewRepository.save(review);
        return convertToDto(result);
    }

    private ReviewDto convertToDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }


}
