package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Yield;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface YieldRepository extends JpaRepository<Yield, Long> {

    @Query("select y from Yield y where y.yieldId = :yieldId")
    Optional<Yield> findByYieldId(Long yieldId);
}
