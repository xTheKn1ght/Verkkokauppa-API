package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByNameContainingIgnoreCase(String name);
}
