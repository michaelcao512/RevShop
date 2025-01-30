package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.CartDto;
import dev.michaelcao512.revshop_springboot.Entities.Cart;
import dev.michaelcao512.revshop_springboot.Entities.User;
import dev.michaelcao512.revshop_springboot.Repositories.CartRepository;
import dev.michaelcao512.revshop_springboot.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart(CartDto response) {
        Cart cart = new Cart();
        User user = userRepository.findById(response.buyerUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setBuyer(user);
        return cartRepository.save(cart);
    }
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

}
