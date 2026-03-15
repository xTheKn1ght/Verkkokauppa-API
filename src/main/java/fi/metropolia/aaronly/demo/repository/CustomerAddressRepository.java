package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
    boolean existsByCustomer_Id(Integer customerId);
    Optional<CustomerAddress> findByCustomer_Id(Integer customerId);
}
