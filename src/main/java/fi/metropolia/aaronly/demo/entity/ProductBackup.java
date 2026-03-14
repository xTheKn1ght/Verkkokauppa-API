package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="products_backup")
public class ProductBackup {
    @Id
    @Column(nullable=false)
    private Integer id;
    @Column(nullable=false, columnDefinition = "VARCHAR(255)")
    private String name;
    @Column
    private String description;
    @Column(nullable = false, precision=10, scale=2)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock_quantity;
    @Column
    private Integer category_id;
    @Column
    private Integer supplier_id;

    public ProductBackup() {}
    public ProductBackup(String name, String description, BigDecimal price, Integer stock_quantity, Integer category_id, Integer supplier_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getStock_quantity() {
        return stock_quantity;
    }
    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    public Integer getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }
}
