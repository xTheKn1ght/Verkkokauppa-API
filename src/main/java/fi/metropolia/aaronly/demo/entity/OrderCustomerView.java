package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name = "view_o_with_c")
public class OrderCustomerView {
    @Id
    @Column(name="order_id")
    private Integer orderId;
    @Column(name="order_date")
    private Date orderDate;
    @Column(name="delivery_date")
    private Date deliveryDate;
    @Column(name="status")
    private String status;
    @Column(name="customer_id")
    private Integer customerId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;

    public Integer getOrderId() { return orderId; }
    public Date getOrderDate() { return orderDate; }
    public Date getDeliveryDate() { return deliveryDate; }
    public String getStatus() { return status; }
    public Integer getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
