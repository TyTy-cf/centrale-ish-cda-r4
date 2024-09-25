
package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Image;
import fr.cda.centaleish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
