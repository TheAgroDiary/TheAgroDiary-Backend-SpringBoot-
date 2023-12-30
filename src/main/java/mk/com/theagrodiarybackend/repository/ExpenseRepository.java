package mk.com.theagrodiarybackend.repository;


import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.dto.ExpenseSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.TotalExpenseSummaryByYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("select e from Expense e where e.expenseId = :expenseId")
    Optional<Expense> findByExpenseId(Integer expenseId);

    @Query("select e from Expense e where e.person.personId = :personId")
    List<Expense> findAllByPerson(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.ExpenseSummaryByYearAndSeed (year(e.date), s.seedName, sum (e.expenseSum)) as totalExpense " +
            "from Expense e " +
            "join Seed s on e.seed.seedId = s.seedId " +
            "where e.person.personId = :personId " +
            "group by 1, 2 " +
            "order by 1 asc ")
    List<ExpenseSummaryByYearAndSeed> expenseSummaryByYearAndSeed(Integer personId);

    @Query("select new mk.com.theagrodiarybackend.model.dto.TotalExpenseSummaryByYear (year(e.date), sum (e.expenseSum)) as totalExpense " +
            "from Expense e " +
            "where e.person.personId = :personId " +
            "group by 1 " +
            "order by 1 asc ")
    List<TotalExpenseSummaryByYear> totalExpenseSummaryByYear(Integer personId);
}
