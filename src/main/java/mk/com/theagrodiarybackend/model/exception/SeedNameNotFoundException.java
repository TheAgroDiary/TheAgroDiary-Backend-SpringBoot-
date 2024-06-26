package mk.com.theagrodiarybackend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SeedNameNotFoundException extends RuntimeException {

    public SeedNameNotFoundException(String seedName) {
        super(String.format("Seed with name: %s not found", seedName));
    }
}