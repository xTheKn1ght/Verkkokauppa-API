package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="orderitems")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name="product_id")
    private Product product;
    @Column(nullable=false)
    private Integer quantity;
    @Column(nullable=false, precision=10, scale=2)
    private BigDecimal unit_price;

    public OrderItem() {}
    public OrderItem(Order order, Product product, Integer quantity, BigDecimal unit_price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.id = new OrderItemId(order.getId(), product.getId());
    }
    public OrderItemId getId() {
        return id;
    }
    public void setId(OrderItemId id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
