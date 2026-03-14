package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
}
