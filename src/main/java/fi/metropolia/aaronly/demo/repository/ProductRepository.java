package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = p.price * 1.1")
    int increaseAllPrices();
    List<Product> findAllByNameContainingIgnoreCase(String name);
    @Query(value = """
SELECT p.name, SUM(oi.quantity) as total_sold FROM orderitems oi JOIN products p ON oi.product_id = p.id GROUP BY p.id ORDER BY total_sold DESC LIMIT 25""", nativeQuery = true)
    List<Object[]> getTopProducts();
}