package spring.airplane.restapi.repository;

import org.springframework.stereotype.Repository;
import spring.airplane.restapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
