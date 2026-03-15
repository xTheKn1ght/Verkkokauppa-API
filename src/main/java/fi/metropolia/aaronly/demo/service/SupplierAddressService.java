package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.SupplierAddress;
import fi.metropolia.aaronly.demo.repository.SupplierAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierAddressService {
    private final SupplierAddressRepository repository;
    public SupplierAddressService(SupplierAddressRepository repository) {
        this.repository = repository;
    }
    public List<SupplierAddress> getAll() {
        return repository.findAll();
    }
    public SupplierAddress save(SupplierAddress supplierAddress) {
        return repository.save(supplierAddress);
    }
    public SupplierAddress update(SupplierAddress supplierAddress, Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SupplierAddress not found");
        }
        supplierAddress.setId(id);
        return repository.save(supplierAddress);
    }
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SupplierAddress not found");
        }
        repository.deleteById(id);
    }
}