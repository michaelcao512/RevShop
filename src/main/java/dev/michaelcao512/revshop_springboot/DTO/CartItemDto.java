package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.CartItem}
 */
public record CartItemDto(Long cartId, Long productId, Integer quantity) implements Serializable {
}