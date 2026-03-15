package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.LowStockProduct;
import fi.metropolia.aaronly.demo.repository.LowStockProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LowStockService {
    private final LowStockProductRepository lowStockRepository;

    public LowStockService(LowStockProductRepository lowStockRepository) {
        this.lowStockRepository = lowStockRepository;
    }

    public List<LowStockProduct> getAllLowStockProducts() {
        return lowStockRepository.findAll();
    }
}
