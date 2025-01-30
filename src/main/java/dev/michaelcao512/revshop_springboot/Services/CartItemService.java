package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.CartItemDto;
import dev.michaelcao512.revshop_springboot.Entities.Cart;
import dev.michaelcao512.revshop_springboot.Entities.CartItem;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Repositories.CartItemRepository;
import dev.michaelcao512.revshop_springboot.Repositories.CartRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    public CartItem createCartItem(CartItemDto request) {
        CartItem cartItem = new CartItem();
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = cartRepository.findById(request.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.quantity());
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItemById(Long cartItemId, CartItemDto request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        Product product = productRepository.findById(request.productId())
                .orElse(null);

        if (request.cartId() != null)
            cartItem.setCartItemId(request.cartId());
        if (product != null)
            cartItem.setProduct(product);
        if (request.quantity() != null)
            cartItem.setQuantity(request.quantity());
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItemById(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
