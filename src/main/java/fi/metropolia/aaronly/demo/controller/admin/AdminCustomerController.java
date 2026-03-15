package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.Customer;
import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/customers")
public class AdminCustomerController {
    private final CustomerService customerService;
    public AdminCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Integer id){
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        Customer saved = customerService.save(customer);
        return ResponseEntity.ok().body(saved);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Integer id){
        try{
            Customer updated = customerService.updateCustomer(customer, id);
            return ResponseEntity.ok().body(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable Integer id){
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
