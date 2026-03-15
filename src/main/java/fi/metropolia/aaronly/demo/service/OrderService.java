package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.Order;
import fi.metropolia.aaronly.demo.entity.OrderItem;
import fi.metropolia.aaronly.demo.entity.OrderItemId;
import fi.metropolia.aaronly.demo.entity.Product;
import fi.metropolia.aaronly.demo.repository.OrderItemRepository;
import fi.metropolia.aaronly.demo.repository.OrderRepository;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // admin methods below
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order updateOrderStatus(Integer id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        return orderRepository.save(order);
    }
    public void deleteOrder(Integer id) {
        if(!orderRepository.existsById(id)){
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    // customer methods below
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public OrderItem createOrderItem(Integer orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepository.findById(orderItem.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        if(product.getStock_quantity() < orderItem.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setId(new OrderItemId(orderId, product.getId()));
        OrderItem saved = orderItemRepository.save(orderItem);

        product.setStock_quantity(product.getStock_quantity() - orderItem.getQuantity());
        productRepository.save(product);
        return saved;
    }
    public void deleteOrderItem(Integer orderId, Integer productId) {
        OrderItemId id = new OrderItemId(orderId, productId);
        if(!orderItemRepository.existsById(id)){
            throw new RuntimeException("OrderItem not found");
        }
        orderItemRepository.deleteById(id);
    }
    public List<OrderItem> getOrderItems(Integer orderId) {
        return orderItemRepository.findByOrder_Id(orderId);
    }
}
