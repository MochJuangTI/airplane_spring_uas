package spring.airplane.restapi.repository;

import org.springframework.stereotype.Repository;
import spring.airplane.restapi.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {
}
