package mk.com.theagrodiarybackend.repository;


import mk.com.theagrodiarybackend.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("select r from Revenue r where r.revenueId = :revenueId")
    Optional<Revenue> findByRevenueId(Long revenueId);
}
