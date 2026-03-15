package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }
    public List<CustomerAddress> getAllAddresses() {
        return customerAddressRepository.findAll();
    }
    public CustomerAddress getAddressByCustomerId(Integer customerId) {
        return customerAddressRepository.findByCustomer_Id(customerId).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public CustomerAddress saveOrUpdateAddress(CustomerAddress address) {
        if (address.getCustomer() == null || address.getCustomer().getId() == null) {
            throw new RuntimeException("Customer must be provided");
        }
        CustomerAddress existing = customerAddressRepository.findByCustomer_Id(address.getCustomer().getId())
                .orElse(null);
        if (existing != null) {
            existing.setStreet_address(address.getStreet_address());
            existing.setPostal_code(address.getPostal_code());
            existing.setCity(address.getCity());
            existing.setCountry(address.getCountry());
            return customerAddressRepository.save(existing);
        } else {
            return customerAddressRepository.save(address);
        }
    }
    public CustomerAddress updateAddress(Integer id, CustomerAddress address) {
        if (!customerAddressRepository.existsById(id)) {
            throw new RuntimeException("Address not found");
        }
        address.setId(id);
        return customerAddressRepository.save(address);
    }
    public void deleteAddress(Integer id) {
        if (!customerAddressRepository.existsById(id)) {
            throw new RuntimeException("Address not found");
        }
        customerAddressRepository.deleteById(id);
    }
}
