package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.converter.SupplierAddressDTO;
import fi.metropolia.aaronly.demo.entity.Supplier;
import fi.metropolia.aaronly.demo.entity.SupplierAddress;
import fi.metropolia.aaronly.demo.repository.SupplierAddressRepository;
import fi.metropolia.aaronly.demo.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierAddressService {
    private final SupplierAddressRepository repository;
    private final SupplierRepository supplierRepository;
    public SupplierAddressService(SupplierAddressRepository repository, SupplierRepository supplierRepository) {
        this.repository = repository;
        this.supplierRepository = supplierRepository;
    }
    public List<SupplierAddress> getAll() {
        return repository.findAll();
    }
    public SupplierAddress save(SupplierAddressDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplier_id()).orElseThrow(() -> new RuntimeException("Supplier not found"));
        SupplierAddress address = new SupplierAddress();
        address.setSupplier(supplier);
        address.setStreet_address(dto.getStreet_address());
        address.setPostal_code(dto.getPostal_code());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());

        return repository.save(address);
    }
    public SupplierAddress update(Integer id, SupplierAddressDTO dto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SupplierAddress not found");
        }
        Supplier supplier = supplierRepository.findById(dto.getSupplier_id()).orElseThrow(() -> new RuntimeException("Supplier not found"));
        SupplierAddress address = repository.findById(id).get();
        address.setSupplier(supplier);
        address.setStreet_address(dto.getStreet_address());
        address.setPostal_code(dto.getPostal_code());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());

        return repository.save(address);
    }
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SupplierAddress not found");
        }
        repository.deleteById(id);
    }
}