package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Review}
 */
public record ReviewDto(Long productId, Long buyerUserId, Integer rating,
                        String comment) implements Serializable {
}