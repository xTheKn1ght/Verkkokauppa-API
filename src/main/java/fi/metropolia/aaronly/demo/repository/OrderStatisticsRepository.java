package fi.metropolia.aaronly.demo.repository;

import fi.metropolia.aaronly.demo.entity.OrderStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;

public interface OrderStatisticsRepository extends JpaRepository<OrderStatistics, Date> {
}
