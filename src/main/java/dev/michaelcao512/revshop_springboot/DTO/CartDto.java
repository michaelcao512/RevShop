package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Cart}
 */
public record CartDto(Long buyerUserId) implements Serializable {
}