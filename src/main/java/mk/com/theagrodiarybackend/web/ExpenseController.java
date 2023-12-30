package mk.com.theagrodiarybackend.web;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.dto.ExpenseDto;
import mk.com.theagrodiarybackend.model.dto.ExpenseSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.TotalExpenseSummaryByYear;
import mk.com.theagrodiarybackend.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9091")
@RequestMapping(path = "/api/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> save(@RequestBody ExpenseDto expenseDto) {
        return this.expenseService.save(expenseDto)
                .map(expense -> ResponseEntity.ok().body(expense))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/my")
    public List<Expense> listAllByPerson (){
        return this.expenseService.findAllByPerson();
    }

    @GetMapping("/statistics1")
    public List<ExpenseSummaryByYearAndSeed> expenseSummaryByYearAndSeed () {
        return this.expenseService.expenseSummaryByYearAndSeed();
    }

    @GetMapping("/statistics2")
    public List<TotalExpenseSummaryByYear> totalExpenseSummaryByYear () {
        return this.expenseService.totalExpenseByYear();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> listById(@PathVariable Integer id) {
        return this.expenseService.findById(id)
                .map(expense -> ResponseEntity.ok().body(expense))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Expense> edit(@PathVariable Integer id, @RequestBody ExpenseDto expenseDto) {
        return this.expenseService.edit(id, expenseDto)
                .map(expense -> ResponseEntity.ok().body(expense))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.expenseService.delete(id);
    }
}
