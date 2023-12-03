package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Integer categoryId) {
        super(String.format("Category with id: %d not found", categoryId));
    }
}
