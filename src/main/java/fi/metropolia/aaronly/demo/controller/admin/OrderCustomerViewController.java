package fi.metropolia.aaronly.demo.controller.admin;


import fi.metropolia.aaronly.demo.entity.OrderCustomerView;
import fi.metropolia.aaronly.demo.repository.OrderCustomerViewRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/orders-with-customers")
public class OrderCustomerViewController {
    private final OrderCustomerViewRepository orderCustomerViewRepository;
    public OrderCustomerViewController(OrderCustomerViewRepository orderCustomerViewRepository) {
        this.orderCustomerViewRepository = orderCustomerViewRepository;
    }
    @GetMapping
    public List<OrderCustomerView> getOrdersWithCustomers() {
        return orderCustomerViewRepository.findAll(Sort.by(Sort.Direction.DESC, "order_date"));
    }
    @GetMapping("/status/{status}")
    public List<OrderCustomerView> getOrdersByStatus(@PathVariable String status) {
        return orderCustomerViewRepository.findByStatus(status);
    }
    @GetMapping("customer/{customerId}")
    public List<OrderCustomerView> getOrdersByCustomerId(@PathVariable Integer customerId) {
        return orderCustomerViewRepository.findByCustomerId(customerId);
    }
}
