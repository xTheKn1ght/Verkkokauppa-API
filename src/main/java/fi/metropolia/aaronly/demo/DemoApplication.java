package fi.metropolia.aaronly.demo;

import fi.metropolia.aaronly.demo.entity.*;
import fi.metropolia.aaronly.demo.repository.ProductCategoryRepository;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import fi.metropolia.aaronly.demo.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    @PersistenceContext
    private EntityManager em;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductService productService) {
        return (args) -> {
            System.out.println("TEST DATABASE");

            /*Customer customer = new Customer("TestiEtunimi", "TestiSukunimi", "testi@testi.fi", "1234567");
            CustomerAddress address = new CustomerAddress("Testikatu 1", "00110", "Testikaupunki", "TestiMaa");
            address.setCustomer(customer);
            customer.setCustomerAddress(address);
            customerRepository.save(customer);*/

            /*System.out.println("prices before");
            productRepository.findAll().forEach(p -> System.out.println(p.getPrice()));
            productService.raisePrices();
            System.out.println("prices after");
            productRepository.findAll().forEach(p -> System.out.println(p.getPrice()));*/

            /*List<Product> results = productService.searchProducts(50.0, 5);

            System.out.println("search results:");
            results.forEach(p -> System.out.println(p.getName()));*/

            /*Product product = new Product("Testing Converter", "Testing Converter", 50.0, 10);
            productRepository.save(product);*/

            /*Product p1 = productRepository.findById(1).get();
            em.clear();
            Product p2 = productRepository.findById(1).get();

            p1.setPrice(100.0);
            productRepository.save(p1);

            p2.setPrice(200.0);
            productRepository.save(p2);*/
        };
    }
}