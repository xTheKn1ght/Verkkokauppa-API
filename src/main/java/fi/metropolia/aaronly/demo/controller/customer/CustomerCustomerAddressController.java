package fi.metropolia.aaronly.demo.controller.customer;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.service.CustomerAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/customer-addresses")
public class CustomerCustomerAddressController {
    private final CustomerAddressService customerAddressService;
    public CustomerCustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getById(@PathVariable Integer id) {
        try {
            CustomerAddress address = customerAddressService.getCustomerAddress(id);
            return ResponseEntity.ok(address);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<CustomerAddress> create(@RequestBody CustomerAddress customerAddress) {
        CustomerAddress saved = customerAddressService.saveCustomerAddress(customerAddress);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> update(@RequestBody CustomerAddress customerAddress, @PathVariable Integer id) {
        try {
            CustomerAddress updated = customerAddressService.updateCustomerAddress(customerAddress, id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            customerAddressService.deleteCustomerAddress(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
