package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
