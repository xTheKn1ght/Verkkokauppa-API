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
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerAddress> get(@PathVariable Integer customerId) {
        try {return ResponseEntity.ok(customerAddressService.getAddressByCustomerId(customerId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerAddress> saveOrUpdate(@RequestBody CustomerAddress address) {
        try {CustomerAddress saved = customerAddressService.saveOrUpdateAddress(address);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerAddress> update(@PathVariable Integer customerId, @RequestBody CustomerAddress address) {
        try {address.setCustomer(address.getCustomer());
            CustomerAddress updated = customerAddressService.saveOrUpdateAddress(address);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Integer customerId) {
        try {
            CustomerAddress existing = customerAddressService.getAddressByCustomerId(customerId);
            customerAddressService.deleteAddress(existing.getId());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}