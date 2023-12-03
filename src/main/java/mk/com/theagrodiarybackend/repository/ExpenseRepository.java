package mk.com.theagrodiarybackend.repository;


import mk.com.theagrodiarybackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("select e from Expense e where e.expenseId = :expenseId")
    Optional<Expense> findByExpenseId(Integer expenseId);

    @Query("select  e from Expense e where e.person.personId = :person_id")
    List<Expense> findAllByPerson(Integer person_id);
}
