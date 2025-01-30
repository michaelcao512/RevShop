package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.CartItemDto;
import dev.michaelcao512.revshop_springboot.Entities.CartItem;
import dev.michaelcao512.revshop_springboot.Services.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {
    private final CartItemService cartItemService;
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

//    get all cart items
    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        return ResponseEntity.ok(cartItemService.getCartItems());
    }

//    get cart item by id
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartItemService.getCartItemById(cartItemId));
    }

//    create cart item given cart id, product id, and quantity
    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItemDto request) {
        return ResponseEntity.ok(cartItemService.createCartItem(request));
    }

//    update cart item given cart item id and new cart item details
    @PatchMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId, @RequestBody CartItemDto request) {
        return ResponseEntity.ok(cartItemService.updateCartItemById(cartItemId, request));
    }

//    delete cart item given cart item id
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItemById(cartItemId);
        return ResponseEntity.noContent().build();
    }

}
