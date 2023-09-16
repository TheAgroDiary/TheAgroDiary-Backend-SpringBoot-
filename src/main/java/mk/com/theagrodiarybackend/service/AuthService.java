package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.dto.PersonDto;
import mk.com.theagrodiarybackend.model.dto.SignupDto;

public interface AuthService {

    PersonDto register(SignupDto signupDto);
}
