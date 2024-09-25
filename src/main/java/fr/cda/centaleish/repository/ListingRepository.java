package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Listing;
import fr.cda.centaleish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

}
