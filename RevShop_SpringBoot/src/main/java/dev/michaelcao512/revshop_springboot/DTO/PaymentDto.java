package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Payment}
 */
public record PaymentDto(String paymentMethod, String cardNumber, String expiryDate, String cvv) implements Serializable {
}