package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Seed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer> {

    @Query("select s from Seed s where s.seedName = :seedName")
    Optional<Seed> findBySeedName(String seedName);
    @Query("select s from Seed s where s.seedId = :seedId")
    Optional<Seed> findBySeedId(Integer seedId);
}
