package fi.metropolia.aaronly.demo.controller.admin;


import fi.metropolia.aaronly.demo.entity.SupplierAddress;
import fi.metropolia.aaronly.demo.service.SupplierAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/supplier-addresses")
public class SupplierAddressController {
    private final SupplierAddressService supplierAddressService;
    public SupplierAddressController(SupplierAddressService supplierAddressService) {
        this.supplierAddressService = supplierAddressService;
    }
    @GetMapping
    public List<SupplierAddress> getAllSupplierAddresses() {
        return supplierAddressService.getAll();
    }
    @PostMapping
    public ResponseEntity<SupplierAddress> createSupplierAddress(@RequestBody SupplierAddress supplierAddress) {
        SupplierAddress saved = supplierAddressService.save(supplierAddress);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SupplierAddress> updateSupplierAddress(@PathVariable Integer id, @RequestBody SupplierAddress supplierAddress) {
        try {
            SupplierAddress updated = supplierAddressService.update(supplierAddress, id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierAddress(@PathVariable Integer id) {
        try {
            supplierAddressService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}