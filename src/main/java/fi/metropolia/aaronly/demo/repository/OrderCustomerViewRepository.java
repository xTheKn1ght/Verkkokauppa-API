package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.OrderCustomerView;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderCustomerViewRepository extends JpaRepository<OrderCustomerView,Integer> {
    List<OrderCustomerView> findByStatus(String status);
    List<OrderCustomerView> findByCustomerId(Integer customer_id);
}
