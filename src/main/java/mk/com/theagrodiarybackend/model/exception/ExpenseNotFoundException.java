package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Integer expenseId) {
        super(String.format("Expense with id: %d not found!", expenseId));
    }
}
