package dev.michaelcao512.revshop_springboot.DTO;

import dev.michaelcao512.revshop_springboot.Entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.User}
 */
public record UserDto(Long userId, @Email String email, @NotNull User.UserType userType) implements Serializable {
}