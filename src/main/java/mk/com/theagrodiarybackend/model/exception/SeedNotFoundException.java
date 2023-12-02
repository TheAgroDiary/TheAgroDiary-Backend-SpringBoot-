package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeedNotFoundException extends RuntimeException {

    public SeedNotFoundException(Integer seedId) {
        super(String.format("Seed with id: %d not found", seedId));
    }
}
