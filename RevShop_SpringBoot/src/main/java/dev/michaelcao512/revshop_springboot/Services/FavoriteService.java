package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.FavoriteDto;
import dev.michaelcao512.revshop_springboot.Entities.Favorite;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Entities.User;
import dev.michaelcao512.revshop_springboot.Repositories.FavoriteRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import dev.michaelcao512.revshop_springboot.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Favorite getFavoriteById(Long favoriteId) {
        return favoriteRepository.findById(favoriteId)
                .orElseThrow((()-> new IllegalArgumentException("Favorite does not exist")));
    }

    public Favorite addFavorite(FavoriteDto request) {
        User user = userRepository.findById(request.buyerUserId())
                .orElseThrow((()-> new IllegalArgumentException("User does not exist")));
        Product product = productRepository.findById(request.productId())
                .orElseThrow((()-> new IllegalArgumentException("Product does not exist")));

        Favorite existingFavorite = favoriteRepository.findByBuyerAndProduct(user, product)
                .orElse(null);
        if (existingFavorite != null) {
//            if favorite exists then un-favorite it (delete)
            favoriteRepository.delete(existingFavorite);
            return null;
        }

        Favorite favorite = new Favorite();
        favorite.setBuyer(user);
        favorite.setProduct(product);

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow((()-> new IllegalArgumentException("Product does not exist")));
        return favoriteRepository.findByProduct(product);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow((()-> new IllegalArgumentException("User does not exist")));
        return favoriteRepository.findByBuyer(user);
    }
}
