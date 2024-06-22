package spring.airplane.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.airplane.restapi.entity.BookingItem;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {
}
