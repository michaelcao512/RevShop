package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Product}
 */
public record ProductDto(Long sellerUserId, String name, String description,
                         Double price, Double discountedPrice, List<Long> categoryIds, InventoryDto inventoryDto) implements Serializable {
}