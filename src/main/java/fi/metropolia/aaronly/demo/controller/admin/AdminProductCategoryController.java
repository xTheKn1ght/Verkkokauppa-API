package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.ProductCategory;
import fi.metropolia.aaronly.demo.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class AdminProductCategoryController {
    private final ProductCategoryService service;
    public AdminProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }
    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategory(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory category) {
        return ResponseEntity.ok(service.create(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateCategory(@PathVariable Integer id, @RequestBody ProductCategory category) {
        try {
            return ResponseEntity.ok(service.update(id, category));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
