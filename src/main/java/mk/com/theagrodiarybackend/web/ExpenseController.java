package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Expense;
import mk.com.theagrodiarybackend.model.dto.ExpenseDto;
import mk.com.theagrodiarybackend.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/edit/{id}")
    public ResponseEntity<Expense> edit(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
        return this.expenseService.edit(id, expenseDto)
                .map(expense -> ResponseEntity.ok().body(expense))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.expenseService.delete(id);
    }
}
