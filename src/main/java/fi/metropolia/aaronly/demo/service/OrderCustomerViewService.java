package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.OrderCustomerView;
import fi.metropolia.aaronly.demo.repository.OrderCustomerViewRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCustomerViewService {
    private final OrderCustomerViewRepository repository;
    public OrderCustomerViewService(OrderCustomerViewRepository repository) {
        this.repository = repository;
    }
    public List<OrderCustomerView> getAllOrders() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
    }
    public List<OrderCustomerView> getOrdersByStatus(String status) {
        return repository.findByStatus(status);
    }
    public List<OrderCustomerView> getOrdersByCustomerId(Integer customerId) {
        return repository.findByCustomerId(customerId);
    }
}