package spring.airplane.restapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.airplane.restapi.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.airplane.restapi.entity.Customer;
import spring.airplane.restapi.entity.Destination;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.date = :date AND b.destination = :destination")
    List<Booking> findByDateAndDestination(@Param("date") LocalDate date, @Param("destination") Destination destination);
    List<Booking> findByCustomer(Customer customer);

}
