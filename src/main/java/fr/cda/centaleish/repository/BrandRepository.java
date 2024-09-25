package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Brand;
import fr.cda.centaleish.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
