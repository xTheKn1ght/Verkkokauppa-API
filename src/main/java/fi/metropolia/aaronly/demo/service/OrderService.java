package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.converter.OrderItemDTO;
import fi.metropolia.aaronly.demo.entity.*;
import fi.metropolia.aaronly.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
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
    public OrderItem createOrderItem(Integer orderId, OrderItemDTO dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock_quantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnit_price(dto.getUnitPrice());
        orderItem.setId(new OrderItemId(orderId, product.getId()));

        OrderItem saved = orderItemRepository.save(orderItem);

        product.setStock_quantity(product.getStock_quantity() - dto.getQuantity());
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
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public CustomerAddress getCustomerAddress(Integer id) {
        return customerAddressRepository.findById(id).orElseThrow(() -> new RuntimeException("Shipping address not found"));
    }
}
