package fi.metropolia.aaronly.demo.controller.admin;


import fi.metropolia.aaronly.demo.entity.OrderCustomerView;
import fi.metropolia.aaronly.demo.service.OrderCustomerViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/views/orders-with-customers")
public class OrderCustomerViewController {
    private final OrderCustomerViewService service;
    public OrderCustomerViewController(OrderCustomerViewService service) {
        this.service = service;
    }
    @GetMapping
    public List<OrderCustomerView> getOrdersWithCustomers() {
        return service.getAllOrders();
    }
    @GetMapping("/status/{status}")
    public List<OrderCustomerView> getOrdersByStatus(@PathVariable String status) {
        return service.getOrdersByStatus(status);
    }
    @GetMapping("/customer/{customerId}")
    public List<OrderCustomerView> getOrdersByCustomerId(@PathVariable Integer customerId) {
        return service.getOrdersByCustomerId(customerId);
    }
}
