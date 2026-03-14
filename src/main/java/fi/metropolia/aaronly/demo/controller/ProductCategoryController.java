package fi.metropolia.aaronly.demo.controller;

import fi.metropolia.aaronly.demo.repository.ProductCategoryRepository;
import fi.metropolia.aaronly.demo.entity.ProductCategory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {
    private final ProductCategoryRepository productCategoryRepository;
    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    @PostMapping
    public ProductCategory createCategory(@RequestBody ProductCategory category) {
        return productCategoryRepository.save(category);
    }
    @GetMapping("/{id}")
    public ProductCategory getCategory(@PathVariable Integer id) {
        return productCategoryRepository.findById(id).orElse(null);
    }
    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }
}
