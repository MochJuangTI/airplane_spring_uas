package spring.airplane.restapi.repository;

import org.springframework.stereotype.Repository;
import spring.airplane.restapi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
