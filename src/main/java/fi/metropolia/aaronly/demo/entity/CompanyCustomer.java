package fi.metropolia.aaronly.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@DiscriminatorValue("COMPANY")
public class CompanyCustomer extends Customer {
    private String businessId;
    public CompanyCustomer() {}
    public CompanyCustomer(String first_name, String last_name, String email, String phone, String businessId) {super(first_name, last_name, email, phone);
    this.businessId = businessId;
    }
    public String getBusinessId() {
        return businessId;
    }
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
