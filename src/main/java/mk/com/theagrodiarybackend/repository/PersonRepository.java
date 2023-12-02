package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);

    @Query("select p from Person p where p.personId = :id")
    Optional<Person> findByPersonId(Integer id);
    @Query("select personId from Person where username = :username")
    Optional<Integer> getPersonIdByUsername(String username);
}
