package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;
    @Basic(optional = false)
    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date delivery_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shipping_address_id")
    private CustomerAddress shippingAddress;
    @Column(columnDefinition = "VARCHAR(50)")
    private String status;

    public Order(){}
    public Order(Date order_date, Date delivery_date, String status) {
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public Date getDelivery_date() {
        return delivery_date;
    }
    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public CustomerAddress getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(CustomerAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
