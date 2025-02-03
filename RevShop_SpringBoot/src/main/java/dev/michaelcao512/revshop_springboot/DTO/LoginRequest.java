package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.User}
 */
public record LoginRequest(String email, String password) implements Serializable {
}