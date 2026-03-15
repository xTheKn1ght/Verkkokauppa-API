package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.ProductCategory;
import fi.metropolia.aaronly.demo.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository repository;
    public ProductCategoryService(ProductCategoryRepository repository) {
        this.repository = repository;
    }
    public List<ProductCategory> getAll() {
        return repository.findAll();
    }
    public ProductCategory getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }
    public ProductCategory create(ProductCategory category) {
        return repository.save(category);
    }
    public ProductCategory update(Integer id, ProductCategory category) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        category.setId(id);
        return repository.save(category);
    }
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
