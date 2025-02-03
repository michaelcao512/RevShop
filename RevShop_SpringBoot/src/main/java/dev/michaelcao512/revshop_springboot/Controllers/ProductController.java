package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.ProductDto;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    gets all products
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

//    creates a product given a seller user id, name, description, and price
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

//    gets a product by its id
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

//    updates a product by its id
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long productId, @RequestBody ProductDto request) {
        return ResponseEntity.ok(productService.updateProductById(productId, request));
    }

//    deletes a product by its id
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

//    get all products that belong to at least one category in the request
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestBody ProductDto request) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(request));
    }

}
