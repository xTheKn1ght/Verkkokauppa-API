package fi.metropolia.aaronly.demo.service;

import fi.metropolia.aaronly.demo.entity.OrderStatistics;
import fi.metropolia.aaronly.demo.repository.OrderStatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatisticsService {
    private final OrderStatisticsRepository repository;
    public OrderStatisticsService(OrderStatisticsRepository repository) {
        this.repository = repository;
    }
    public List<OrderStatistics> getAllStatistics() {
        return repository.findAll();
    }
}