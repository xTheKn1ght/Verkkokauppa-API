package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.CustomerAddress;
import fi.metropolia.aaronly.demo.repository.CustomerAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }
    public List<CustomerAddress> getCustomerAdresses(){
        return customerAddressRepository.findAll();
    }
    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress){
        return customerAddressRepository.save(customerAddress);
    }
    public CustomerAddress updateCustomerAddress(CustomerAddress customerAddress, Integer id){
        if(!customerAddressRepository.existsById(id)){
            throw new RuntimeException("Customer address not found");
        }
        customerAddress.setId(id);
        return customerAddressRepository.save(customerAddress);
    }
    public void deleteCustomerAddress(Integer id){
        if(!customerAddressRepository.existsById(id)){
            throw new RuntimeException("Customer address not found");
        }
        customerAddressRepository.deleteById(id);
    }
    public CustomerAddress getCustomerAddress(Integer id){
        return customerAddressRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer address not found"));
    }
}
