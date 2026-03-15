package fi.metropolia.aaronly.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import fi.metropolia.aaronly.demo.converter.StockConverter;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    private Double price;
    @Column(nullable = false)
    @Convert(converter = StockConverter.class)
    private Integer stock_quantity;
    @Version
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private ProductCategory category;

    @ManyToMany
    @JoinTable(name= "product_supplier", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<Supplier> suppliers = new HashSet<>();

    public Product() {}
    public Product(String name, String description, Double price, Integer stock_quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
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

    public ProductCategory getCategory() {
        return category;
    }
    public void setCategory(ProductCategory category) {
        this.category = category;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getStock_quantity() {
        return stock_quantity;
    }
    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
    public Integer getVersion() {
        return version;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    /*
    @PrePersist
    @PreUpdate
    public void validatePrice() {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
    */
}