package dev.michaelcao512.revshop_springboot.Controllers;

import dev.michaelcao512.revshop_springboot.DTO.CategoryDto;
import dev.michaelcao512.revshop_springboot.Entities.Category;
import dev.michaelcao512.revshop_springboot.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    gets all categories
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

//    creates a category given a name and description
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

//   gets a category by its id
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

//    updates a category by its id
    @PatchMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long categoryId, @RequestBody CategoryDto request) {
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryId, request));
    }

//    deletes a category by its id
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}
