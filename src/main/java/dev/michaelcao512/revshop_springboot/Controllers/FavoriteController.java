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

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }
    @GetMapping("/{favoriteId}")
    public Favorite getFavoriteById(@PathVariable Long favoriteId) {
        return favoriteService.getFavoriteById(favoriteId);
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody FavoriteDto request) {
        return favoriteService.addFavorite(request);
    }

    @GetMapping("/product/{productId}")
    public List<Favorite> getFavoritesByProduct(@PathVariable Long productId) {
        return favoriteService.getFavoritesByProduct(productId);
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }


}
