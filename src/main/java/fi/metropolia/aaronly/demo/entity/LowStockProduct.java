package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "view_low_stock_products")
public class LowStockProduct {
    @Id
    private Integer id;

    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private Integer category_id;
    private Integer supplier_id;
    private Integer version;

    public LowStockProduct() {}
    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public Double getPrice(){
        return price;
    }
    public Integer getStock_quantity(){
        return stock_quantity;
    }
    public Integer getCategory_id(){
        return category_id;
    }
    public Integer getSupplier_id(){
        return supplier_id;
    }
    public Integer getVersion(){
        return version;
    }
}
