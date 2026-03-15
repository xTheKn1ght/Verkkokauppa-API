package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.Customer;
import fi.metropolia.aaronly.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    public Customer findById(Integer id){
        return customerRepository.findById(id).get();
    }
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer, Integer id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found");
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }
    public void deleteCustomer(Integer id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
