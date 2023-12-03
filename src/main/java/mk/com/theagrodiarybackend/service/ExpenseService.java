package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.dto.ExpenseDto;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    List<Expense> findAll();
    List<Expense> findAllByPerson();
    Optional<Expense> findById(Integer expenseId);
    Optional<Expense> save(ExpenseDto expenseDto);
    Optional<Expense> edit(Integer expenseId, ExpenseDto expenseDto);
    void delete(Integer expenseId);
}
