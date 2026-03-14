package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.Supplier;
import fi.metropolia.aaronly.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/suppliers")
public class SupplierController {
    private final SupplierRepository supplierRepository;
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    @GetMapping
    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }
    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Integer id) {
        supplierRepository.deleteById(id);
    }
}
