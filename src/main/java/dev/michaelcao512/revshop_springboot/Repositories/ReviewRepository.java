package dev.michaelcao512.revshop_springboot.Repositories;

import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Entities.Review;
import dev.michaelcao512.revshop_springboot.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByBuyerAndProduct(User user, Product product);

    List<Review> findByProduct(Product product);

    List<Review> findByBuyer(User user);
}