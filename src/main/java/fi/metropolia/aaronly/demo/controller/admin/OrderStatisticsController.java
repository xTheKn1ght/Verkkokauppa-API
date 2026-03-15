package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.OrderStatistics;
import fi.metropolia.aaronly.demo.service.OrderStatisticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/views")
public class OrderStatisticsController {
    private final OrderStatisticsService service;
    public OrderStatisticsController(OrderStatisticsService service) {
        this.service = service;
    }
    @GetMapping("/order-statistics")
    public List<OrderStatistics> getOrderStatistics() {
        return service.getAllStatistics();
    }
}