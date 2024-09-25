
package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Address;
import fr.cda.centaleish.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
