package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.converter.CustomerOrderDTO;
import fi.metropolia.aaronly.demo.converter.OrderItemDTO;
import fi.metropolia.aaronly.demo.entity.*;
import fi.metropolia.aaronly.demo.repository.OrderItemRepository;
import fi.metropolia.aaronly.demo.repository.OrderRepository;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import fi.metropolia.aaronly.demo.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer/orders")
public class CustomerOrderController {
    private final OrderService orderService;
    public CustomerOrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CustomerOrderDTO dto) {
        Order order = new Order();
        order.setStatus(dto.getStatus());
        Customer customer = orderService.getCustomerById(dto.getCustomerId());
        CustomerAddress address = orderService.getCustomerAddress(dto.getShippingAddressId());
        order.setCustomer(customer);
        order.setShippingAddress(address);
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItem> createOrderItem(@PathVariable Integer orderId, @RequestBody OrderItemDTO dto) {
        try {
            return ResponseEntity.ok(orderService.createOrderItem(orderId, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/{orderId}/items/{productId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer orderId, @PathVariable Integer productId) {
        try {
            orderService.deleteOrderItem(orderId, productId);
            return ResponseEntity.noContent().build();
        }   catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable Integer orderId) {
        return orderService.getOrderItems(orderId);
    }
}
