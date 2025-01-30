package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Inventory}
 */
public record InventoryDto(Integer quantity, Integer threshold) implements Serializable {
}