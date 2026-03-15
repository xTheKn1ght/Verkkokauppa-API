package fi.metropolia.aaronly.demo.converter;

import java.util.Set;

public class ProductUpdateDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private Integer categoryId;
    private Set<Integer> supplierIds;
    public ProductUpdateDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getStock_quantity() { return stock_quantity; }
    public void setStock_quantity(Integer stock_quantity) { this.stock_quantity = stock_quantity; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public Set<Integer> getSupplierIds() { return supplierIds; }
    public void setSupplierIds(Set<Integer> supplierIds) { this.supplierIds = supplierIds; }
}