package fi.metropolia.aaronly.demo.entity;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemId implements Serializable {
    private Integer order_id;
    private Integer product_id;

    public OrderItemId() {}
    public OrderItemId(Integer order_id, Integer product_id) {
        this.order_id = order_id;
        this.product_id = product_id;
    }
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Integer getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId)) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(order_id, that.order_id) &&
                Objects.equals(product_id, that.product_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(order_id, product_id);
    }
}
