package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Integer> {

    @Query("select p from Plantation p where p.plantationId = :plantationId")
    Optional<Plantation> findByPlantationId(Integer plantationId);

    @Query("select p from Plantation p where p.person.personId = :person_id")
    List<Plantation> findAllByPerson(Integer person_id);
}
