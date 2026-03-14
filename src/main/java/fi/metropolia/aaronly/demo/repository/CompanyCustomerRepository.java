package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.CompanyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyCustomerRepository extends JpaRepository<CompanyCustomer, Integer> {
}
