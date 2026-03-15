package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.LowStockProduct;
import fi.metropolia.aaronly.demo.service.LowStockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/views")
public class LowStockController {
    private final LowStockService lowStockService;
    public LowStockController(LowStockService lowStockService) {
        this.lowStockService = lowStockService;
    }
    @GetMapping("/low-stock")
    public List<LowStockProduct> getLowStockProducts() {
        return lowStockService.getAllLowStockProducts();
    }
}
