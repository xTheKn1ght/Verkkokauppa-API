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
    /*
    select p.name, sum(oi.quantity) as total_sold from orderitems oi join products p on oi.product_id = p.id group by p.id order by total_sold desc limit 25; eli top 25 myydyintä
    */
}