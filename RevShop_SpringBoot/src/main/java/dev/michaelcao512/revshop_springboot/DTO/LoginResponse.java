package dev.michaelcao512.revshop_springboot.DTO;

import dev.michaelcao512.revshop_springboot.Entities.User;

public record LoginResponse(String jwt, User.UserType userType, Long userId) {
}
