package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Fuel;
import fr.cda.centaleish.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {

}
