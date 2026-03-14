package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.ProductBackup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBackupRepository extends JpaRepository<ProductBackup,Integer> {
}
