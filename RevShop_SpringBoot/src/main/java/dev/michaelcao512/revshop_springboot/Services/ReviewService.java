package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.ReviewDto;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Entities.Review;
import dev.michaelcao512.revshop_springboot.Entities.User;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ReviewRepository;
import dev.michaelcao512.revshop_springboot.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review createReview(ReviewDto review) {
        User user = userRepository.findById(review.buyerUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(review.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review existingReview = reviewRepository.findByBuyerAndProduct(user, product)
                .orElse(null);
        if (existingReview != null) {
            existingReview.setRating(review.rating());
            existingReview.setComment(review.comment());
            return reviewRepository.save(existingReview);
        }

        Review newReview = new Review();
        newReview.setBuyer(user);
        newReview.setProduct(product);
        newReview.setRating(review.rating());
        newReview.setComment(review.comment());
        return reviewRepository.save(newReview);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<Review> getReviewsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return reviewRepository.findByProduct(product);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reviewRepository.findByBuyer(user);
    }
}
