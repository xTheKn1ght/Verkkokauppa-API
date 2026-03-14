package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.entity.Product;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer/products")
public class CustomerProductController {
    private final ProductRepository productRepository;
    public CustomerProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
