package spring.airplane.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.airplane.restapi.entity.CreditHistory;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, Integer> {
}
