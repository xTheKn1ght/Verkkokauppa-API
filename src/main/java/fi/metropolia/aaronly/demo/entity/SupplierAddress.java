package fi.metropolia.aaronly.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="supplieraddresses")
public class SupplierAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;
    @Column(nullable = false, columnDefinition="VARCHAR(255)")
    private String street_address;
    @Column(columnDefinition = "VARCHAR(20)")
    private String postal_code;
    @Column(nullable = false, columnDefinition="VARCHAR(100)")
    private String city;
    @Column(columnDefinition="VARCHAR(100)")
    private String country;

    public SupplierAddress() {}
    public SupplierAddress(String street_address, String postal_code, String city, String country) {
        this.street_address = street_address;
        this.postal_code = postal_code;
        this.city = city;
        this.country = country;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStreet_address() {
        return street_address;
    }
    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }
    public String getPostal_code() {
        return postal_code;
    }
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
