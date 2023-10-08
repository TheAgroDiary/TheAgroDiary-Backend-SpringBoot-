package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.dto.ExpenseDto;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    List<Expense> findAll();
    Optional<Expense> findById(Long expenseId);
    Optional<Expense> save(ExpenseDto expenseDto);
    Optional<Expense> edit(Long expenseId, ExpenseDto expenseDto);
    void delete(Long expenseId);
}
