package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class YieldNotFountException extends RuntimeException {

    public YieldNotFountException(Long yieldId) {
        super(String.format("Yield with id: %d not found", yieldId));
    }
}
