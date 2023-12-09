package mk.com.theagrodiarybackend.repository;


import mk.com.theagrodiarybackend.model.Revenue;
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
}
