package spring.airplane.restapi.repository;

import org.springframework.stereotype.Repository;
import spring.airplane.restapi.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
