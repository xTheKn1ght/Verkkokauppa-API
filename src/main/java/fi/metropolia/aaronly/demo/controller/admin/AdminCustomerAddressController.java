package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.service.CustomerAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/customer-addresses")
public class AdminCustomerAddressController {
    private final CustomerAddressService customerAddressService;
    public AdminCustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }
    @GetMapping
    public List<CustomerAddress> getAll() {
        return customerAddressService.getAllAddresses();
    }
    @PostMapping
    public ResponseEntity<CustomerAddress> create(@RequestBody CustomerAddress address) {
        try {
            CustomerAddress saved = customerAddressService.saveOrUpdateAddress(address);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> update(@PathVariable Integer id, @RequestBody CustomerAddress address) {
        try {
            CustomerAddress updated = customerAddressService.updateAddress(id, address);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            customerAddressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
