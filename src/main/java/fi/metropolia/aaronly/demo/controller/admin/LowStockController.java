package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.LowStockProduct;
import fi.metropolia.aaronly.demo.repository.LowStockProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class LowStockController {
    private final LowStockProductRepository lowStockRepository;
    public LowStockController(LowStockProductRepository lowStockRepository) {
        this.lowStockRepository = lowStockRepository;
    }
    @GetMapping("/low-stock")
    public List<LowStockProduct> getLowStockProducts() {
        return lowStockRepository.findAll();
    }
}
