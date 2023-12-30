package mk.com.theagrodiarybackend.repository;

import jakarta.persistence.Tuple;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeedAndType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Integer> {

    @Query("select p from Plantation p where p.plantationId = :plantationId")
    Optional<Plantation> findByPlantationId(Integer plantationId);

    @Query("select p from Plantation p where p.person.personId = :personId")
    List<Plantation> findAllByPerson(Integer personId);

    @Query("select p.year, s.seedName, sum (p.amountKg) as totalAmountKg " +
            "from Plantation p " +
            "join Seed s on p.seed.seedId = s.seedId " +
            "where p.person.personId = :personId " +
            "group by p.year, s.seedName " +
            "order by 1 asc")
    List<Tuple> sumPlantationsByYearAndSeed(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeedAndType (p.year, s.seedName, p.type, sum (p.amountKg)) as totalAmountKg " +
            "from Plantation p " +
            "join Seed s on p.seed.seedId = s.seedId " +
            "where p.person.personId = :personId " +
            "group by p.year, s.seedName, p.type " +
            "order by 1 asc")
    List<PlantationSummaryByYearAndSeedAndType> sumPlantationsByYearAndSeedAAndType(Integer personId);
}
