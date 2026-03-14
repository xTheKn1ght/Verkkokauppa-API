package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name = "view_order_statistics")
public class OrderStatistics {
    @Id
    private Date order_day;
    private Long orders_count;

    public OrderStatistics(){

    }

    public Date getOrder_day() {
        return order_day;
    }

    public void setOrder_day(Date order_day) {
        this.order_day = order_day;
    }

    public Long getOrdersCount() {
        return orders_count;
    }
    public void setOrders_count(Long orders_count) {
        this.orders_count = orders_count;
    }
}