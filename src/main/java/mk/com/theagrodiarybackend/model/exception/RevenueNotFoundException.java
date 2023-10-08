package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RevenueNotFoundException extends RuntimeException {

    public RevenueNotFoundException(Long revenueId) {
        super(String.format("Revenue with id: %d not found!", revenueId));
    }
}
