package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.repository.CustomerAddressRepository;
import fi.metropolia.aaronly.demo.service.CustomerAddressService;
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
    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddressService.getCustomerAdresses();
    }
    @PostMapping
    public ResponseEntity<CustomerAddress> createCustomerAddress(@RequestBody CustomerAddress customerAddress) {
        CustomerAddress saved = customerAddressService.saveCustomerAddress(customerAddress);
        return ResponseEntity.ok().body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> updateCustomerAddress(@RequestBody CustomerAddress customerAddress, @PathVariable Integer id) {
        try {
            CustomerAddress updated = customerAddressService.updateCustomerAddress(customerAddress, id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteCustomerAddress(@PathVariable Integer id) {
        try {
            customerAddressService.deleteCustomerAddress(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
