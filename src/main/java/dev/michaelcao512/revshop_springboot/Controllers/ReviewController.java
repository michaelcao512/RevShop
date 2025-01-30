package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.ReviewDto;
import dev.michaelcao512.revshop_springboot.Entities.Review;
import dev.michaelcao512.revshop_springboot.Services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }
}
