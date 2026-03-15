package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.Product;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final EntityManager em;
    public ProductService(ProductRepository productRepository, EntityManager em) {
        this.productRepository = productRepository;
        this.em = em;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Integer id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        product.setId(id);
        return productRepository.save(product);
    }
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
    @Transactional
    public void increasePrices() {
        productRepository.increaseAllPrices();
    }
    @Transactional
    public List<Product> searchProducts(Double minPrice, Integer minStock) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (minStock != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("stock_quantity"), minStock));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
