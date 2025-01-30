package dev.michaelcao512.revshop_springboot.Services;

import dev.michaelcao512.revshop_springboot.DTO.ProductDto;
import dev.michaelcao512.revshop_springboot.Entities.Category;
import dev.michaelcao512.revshop_springboot.Entities.Product;
import dev.michaelcao512.revshop_springboot.Entities.User;
import dev.michaelcao512.revshop_springboot.Repositories.CategoryRepository;
import dev.michaelcao512.revshop_springboot.Repositories.ProductRepository;
import dev.michaelcao512.revshop_springboot.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));
    }

    public Product createProduct(ProductDto request) {
        Product product = new Product();
        User seller = userRepository.findById(request.sellerUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + request.sellerUserId() + " not found"));
        List<Category> categories = categoryRepository.findByCategoryIdIn(request.categoryIds());

        product.setSeller(seller);
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setDiscountedPrice(request.discountedPrice() == 0 ? request.price() : request.discountedPrice());
        product.setCategories(List.copyOf(categories));
        return productRepository.save(product);
    }

    public Product updateProductById(Long productId, ProductDto request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with id " + productId + " not found"));

        if (request.name() != null)
            product.setName(request.name());
        if (request.description() != null)
            product.setDescription(request.description());
        if (request.price() != 0)
            product.setPrice(request.price());
        if (request.discountedPrice() != 0)
            product.setDiscountedPrice(request.discountedPrice());
        if (request.categoryIds() != null) {
            List<Category> categories = categoryRepository.findByCategoryIdIn(request.categoryIds());
            product.setCategories(List.copyOf(categories));
        }
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByCategoryId(ProductDto request) {
        return productRepository.findByCategoriesIds(request.categoryIds());
    }
}
