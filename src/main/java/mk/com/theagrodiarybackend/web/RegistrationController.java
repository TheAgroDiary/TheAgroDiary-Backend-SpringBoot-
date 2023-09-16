package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.dto.PersonDto;
import mk.com.theagrodiarybackend.model.dto.SignupDto;
import mk.com.theagrodiarybackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:9091")
@RequestMapping(path = "/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody SignupDto signupDto) {
        PersonDto personDto = authService.register(signupDto);
        if (personDto == null){
            return new ResponseEntity<>("User not created, try again!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personDto, HttpStatus.CREATED);
    }
}