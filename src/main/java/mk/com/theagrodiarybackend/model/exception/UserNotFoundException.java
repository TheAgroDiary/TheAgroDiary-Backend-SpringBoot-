package mk.com.theagrodiarybackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super(String.format("User with user_id: %d not found", id));
    }
}
