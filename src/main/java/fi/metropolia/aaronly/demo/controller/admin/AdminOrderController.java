package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.Order;
import fi.metropolia.aaronly.demo.service.OrderService;
import fi.metropolia.aaronly.demo.converter.StatusUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
    private final OrderService orderService;
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Integer id, @RequestBody StatusUpdate statusUpdate) {
        try{
            return ResponseEntity.ok(orderService.updateOrderStatus(id, statusUpdate.getStatus()));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        try{
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
