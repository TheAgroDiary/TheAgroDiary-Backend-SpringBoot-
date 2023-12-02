package mk.com.theagrodiarybackend.model.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlantationNotFoundException extends RuntimeException {

    public PlantationNotFoundException(Integer plantationIn) {
        super(String.format("Plantation with id: %d not found", plantationIn));
    }
}
