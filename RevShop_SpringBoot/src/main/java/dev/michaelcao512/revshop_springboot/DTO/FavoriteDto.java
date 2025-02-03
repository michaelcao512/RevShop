package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Favorite}
 */
public record FavoriteDto(Long buyerUserId, Long productId) implements Serializable {
}