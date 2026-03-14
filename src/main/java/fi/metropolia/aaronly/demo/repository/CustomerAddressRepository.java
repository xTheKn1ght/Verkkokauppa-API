package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
}
