package mk.com.theagrodiarybackend.repository;


import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.TotalRevenueSummaryByYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

    @Query("select r from Revenue r where r.revenueId = :revenueId")
    Optional<Revenue> findByRevenueId(Integer revenueId);

    @Query("select r from Revenue r where r.person.personId = :personId")
    List<Revenue> findAllByPerson(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.RevenueSummaryByYearAndSeed (year(r.date), s.seedName, sum (r.revenueSum)) as totalRevenue " +
            "from Revenue r " +
            "join Seed s on r.seed.seedId = s.seedId " +
            "where r.person.personId = :personId " +
            "group by 1, 2 " +
            "order by 1 asc ")
    List<RevenueSummaryByYearAndSeed> revenueSummaryByYearAndSeed(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.TotalRevenueSummaryByYear (year(r.date), sum (r.revenueSum)) as totalRevenue " +
            "from Revenue r " +
            "where r.person.personId = :personId " +
            "group by 1 " +
            "order by 1 asc ")
    List<TotalRevenueSummaryByYear> totalRevenueSummaryByYear(Integer personId);
}
