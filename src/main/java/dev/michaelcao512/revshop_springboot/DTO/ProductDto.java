package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Product}
 */
public record ProductDto(Long sellerUserId, String name, String description,
                         double price, double discountedPrice, List<Long> categoryIds) implements Serializable {
}