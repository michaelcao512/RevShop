package dev.michaelcao512.revshop_springboot.DTO;

import java.io.Serializable;

/**
 * DTO for {@link dev.michaelcao512.revshop_springboot.Entities.Order}
 */
public record OrderDto(Long cartId, Long buyerUserId, String shippingAddress, String billingAddress, PaymentDto paymentDto) implements Serializable {
}