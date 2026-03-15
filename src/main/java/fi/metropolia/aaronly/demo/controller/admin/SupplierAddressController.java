package fi.metropolia.aaronly.demo.controller.admin;


import fi.metropolia.aaronly.demo.converter.SupplierAddressDTO;
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
    public ResponseEntity<SupplierAddress> createSupplierAddress(@RequestBody SupplierAddressDTO dto) {
        SupplierAddress saved = supplierAddressService.save(dto);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SupplierAddress> updateSupplierAddress(@PathVariable Integer id, @RequestBody SupplierAddressDTO dto) {
        try {
            SupplierAddress updated = supplierAddressService.update(id, dto);
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