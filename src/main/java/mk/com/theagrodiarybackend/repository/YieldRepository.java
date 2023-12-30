package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Yield;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeedAndType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface YieldRepository extends JpaRepository<Yield, Integer> {

    @Query("select y from Yield y where y.yieldId = :yieldId")
    Optional<Yield> findByYieldId(Integer yieldId);

    @Query("select y from Yield y where y.person.personId = :personId")
    List<Yield> findAllByPerson(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeed (y.year, s.seedName, sum (y.amountKg)) as totalAmountKg " +
            "from Yield y " +
            "join Seed s on y.seed.seedId = s.seedId " +
            "where y.person.personId = :personId " +
            "group by y.year, s.seedName " +
            "order by y.year asc")
    List<YieldSummaryByYearAndSeed> sumYieldByYearAndSeed(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeedAndType (y.year, s.seedName, y.type, sum (y.amountKg)) as totalAmountKg " +
            "from Yield y " +
            "join Seed s on y.seed.seedId = s.seedId " +
            "where y.person.personId = :personId " +
            "group by y.year, s.seedName, y.type " +
            "order by y.year asc")
    List<YieldSummaryByYearAndSeedAndType> sumYieldByYearAndSeedAndType(Integer personId);
}
