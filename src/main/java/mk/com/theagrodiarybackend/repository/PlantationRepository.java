package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {

    @Query("select p from Plantation p where p.plantationId = :plantationId")
    Optional<Plantation> findByPlantationId(Long plantationId);
}
