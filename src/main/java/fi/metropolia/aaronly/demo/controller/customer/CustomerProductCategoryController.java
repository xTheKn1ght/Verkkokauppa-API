package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.entity.ProductCategory;
import fi.metropolia.aaronly.demo.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer/categories")
public class CustomerProductCategoryController {
    private final ProductCategoryService service;
    public CustomerProductCategoryController(ProductCategoryService service) {
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
}