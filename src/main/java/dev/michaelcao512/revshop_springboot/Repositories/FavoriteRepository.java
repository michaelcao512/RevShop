package dev.michaelcao512.revshop_springboot.Repositories;

import dev.michaelcao512.revshop_springboot.Entities.Favorite;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByBuyerAndProduct(User user, Product product);

    List<Favorite> findByProduct(Product product);

    List<Favorite> findByBuyer(User user);
}