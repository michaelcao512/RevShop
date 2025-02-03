package dev.michaelcao512.revshop_springboot.DTO;

import dev.michaelcao512.revshop_springboot.Entities.User;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.User}
 */
public record RegistrationRequest(String email, String password, User.UserType userType) implements Serializable {
}