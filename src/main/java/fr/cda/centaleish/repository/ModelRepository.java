package fr.cda.centaleish.repository;

import fr.cda.centaleish.entity.Model;
import fr.cda.centaleish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
