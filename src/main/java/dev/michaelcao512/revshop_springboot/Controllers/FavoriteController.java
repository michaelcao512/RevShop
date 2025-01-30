package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.FavoriteDto;
import dev.michaelcao512.revshop_springboot.Entities.Favorite;
import dev.michaelcao512.revshop_springboot.Services.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

//    get all favorites
    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

//    get favorite by id
    @GetMapping("/{favoriteId}")
    public Favorite getFavoriteById(@PathVariable Long favoriteId) {
        return favoriteService.getFavoriteById(favoriteId);
    }

//    users adds product as favorite or remove product from favorite (is Favorite already exists)
    @PutMapping
    public Favorite addFavorite(@RequestBody FavoriteDto request) {
        return favoriteService.addFavorite(request);
    }

//    get all favorites by product id
    @GetMapping("/product/{productId}")
    public List<Favorite> getFavoritesByProduct(@PathVariable Long productId) {
        return favoriteService.getFavoritesByProduct(productId);
    }

//    get all favorites by user id
    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }


}
