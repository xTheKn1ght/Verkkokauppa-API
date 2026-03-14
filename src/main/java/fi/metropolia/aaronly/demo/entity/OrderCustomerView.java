package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name = "view_o_with_c")
public class OrderCustomerView {
    @Id
    private Integer order_id;
    private Date order_date;
    private Date delivery_date;
    private String status;
    private Integer customer_id;
    private String first_name;
    private String last_name;
    private String email;

    public OrderCustomerView() {}

    public Integer getOrder_id() {
        return order_id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public Date getDelivery_date() {
        return delivery_date;
    }
    public String getStatus() {
        return status;
    }
    public Integer getCustomer_id() {
        return customer_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public String getEmail() {
        return email;
    }
}
