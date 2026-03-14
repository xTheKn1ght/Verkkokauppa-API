package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.entity.Order;
import fi.metropolia.aaronly.demo.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer/orders")
public class CustomerOrderController {
    private final OrderRepository orderRepository;
    public CustomerOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
