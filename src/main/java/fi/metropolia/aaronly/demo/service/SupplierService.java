package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.Supplier;
import fi.metropolia.aaronly.demo.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository repository;
    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }
    public List<Supplier> getAll() {
        return repository.findAll();
    }
    public Supplier save(Supplier supplier) {
        return repository.save(supplier);
    }
    public Supplier update(Supplier supplier, Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        supplier.setId(id);
        return repository.save(supplier);
    }
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        repository.deleteById(id);
    }
}