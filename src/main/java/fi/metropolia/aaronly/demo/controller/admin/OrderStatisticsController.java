package fi.metropolia.aaronly.demo.controller.admin;

import fi.metropolia.aaronly.demo.entity.OrderCustomerView;
import fi.metropolia.aaronly.demo.repository.OrderStatisticsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class OrderStatisticsController {
    private final OrderStatisticsRepository orderStatisticsRepository;
    public OrderStatisticsController(OrderStatisticsRepository orderStatisticsRepository) {
        this.orderStatisticsRepository = orderStatisticsRepository;
    }
    @GetMapping("/order-statistics")
    public List<OrderCustomerView> getOrderStatistics() {
        return orderStatisticsRepository.findAll();
    }
}
