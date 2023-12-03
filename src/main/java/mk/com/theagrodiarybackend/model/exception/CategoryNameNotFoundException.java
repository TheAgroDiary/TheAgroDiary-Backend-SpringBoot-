package mk.com.theagrodiarybackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNameNotFoundException extends RuntimeException {

    public CategoryNameNotFoundException(String categoryName) {
        super(String.format("Category with name: %s not found", categoryName));
    }
}