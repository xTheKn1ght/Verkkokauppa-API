package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.converter.ProductUpdateDTO;
import fi.metropolia.aaronly.demo.entity.Product;
import fi.metropolia.aaronly.demo.entity.ProductCategory;
import fi.metropolia.aaronly.demo.entity.Supplier;
import fi.metropolia.aaronly.demo.repository.ProductCategoryRepository;
import fi.metropolia.aaronly.demo.repository.ProductRepository;
import fi.metropolia.aaronly.demo.repository.SupplierRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;
    private final EntityManager em;
    public ProductService(ProductRepository productRepository,  ProductCategoryRepository productCategoryRepository, SupplierRepository supplierRepository, EntityManager em) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.supplierRepository = supplierRepository;
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
    @Transactional
    public Product updateProduct(Integer id, ProductUpdateDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock_quantity(dto.getStock_quantity());
        if(dto.getCategoryId() != null){
            ProductCategory category = productCategoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        if (dto.getSupplierIds() != null && !dto.getSupplierIds().isEmpty()) {
            Set<Supplier> suppliers = dto.getSupplierIds().stream().map(supplierId -> supplierRepository.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"))).collect(Collectors.toSet());
            product.setSuppliers(suppliers);
        }
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
    public List<Product> searchProducts(String name, Double minPrice, Integer minStock) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
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
