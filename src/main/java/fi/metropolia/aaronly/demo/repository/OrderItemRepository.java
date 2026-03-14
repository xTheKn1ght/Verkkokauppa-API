package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.OrderItem;
import fi.metropolia.aaronly.demo.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
