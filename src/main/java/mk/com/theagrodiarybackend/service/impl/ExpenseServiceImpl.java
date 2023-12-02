package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.ExpenseDto;
import mk.com.theagrodiarybackend.model.exception.ExpenseNotFoundException;
import mk.com.theagrodiarybackend.model.exception.RevenueNotFoundException;
import mk.com.theagrodiarybackend.model.exception.SeedNotFoundException;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.repository.ExpenseRepository;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.repository.SeedRepository;
import mk.com.theagrodiarybackend.service.ExpenseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private PersonRepository personRepository;
    private SeedRepository seedRepository;

    @Override
    public List<Expense> findAll() {
        return this.expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> findById(Integer expenseId) {
        return Optional.of(this.expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId)));
    }

    @Override
    public Optional<Expense> save(ExpenseDto expenseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = new Person();
        Seed seed = this.seedRepository.findBySeedId(expenseDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(expenseDto.getSeedId()));

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " +username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            System.out.println("Person id is " +personId);
            person = this.personRepository.findByPersonId(personId)
                    .orElseThrow(() -> new UserNotFoundException(personId));
        }
        else {
            System.out.println("Could not retrieve information for authenticated user!");
            person = this.personRepository.findByPersonId(expenseDto.getPersonId())
                    .orElseThrow(() -> new UserNotFoundException(expenseDto.getPersonId()));
        }

        Expense expense = new Expense(
                expenseDto.getExpenseSum(), expenseDto.getDate(), expenseDto.getSeedAmountKg(),
                expenseDto.getExpenseType(), expenseDto.getDescription(), person, seed);
        this.expenseRepository.save(expense);
        return Optional.of(expense);
    }

    @Override
    public Optional<Expense> edit(Integer expenseId, ExpenseDto expenseDto) {
        Expense expense = this.expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        Seed seed = this.seedRepository.findBySeedId(expenseDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(expenseDto.getSeedId()));
        expense.setExpenseSum(expenseDto.getExpenseSum());
        expense.setDate(expenseDto.getDate());
        expense.setSeedAmountKg(expenseDto.getSeedAmountKg());
        expense.setExpenseType(expenseDto.getExpenseType());
        expense.setDescription(expenseDto.getDescription());
        expense.setSeed(seed);
        this.expenseRepository.save(expense);
        return Optional.of(expense);
    }

    @Override
    public void delete(Integer expenseId) {
        Expense expense = this.expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
        this.expenseRepository.delete(expense);
    }
}
