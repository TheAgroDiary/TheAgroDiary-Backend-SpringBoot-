package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.dto.PersonDto;
import mk.com.theagrodiarybackend.model.dto.SignupDto;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private PersonRepository personRepository;

    @Override
    public PersonDto register(SignupDto signupDto) {
        Person person = new Person();
        person.setFirstName(signupDto.getFirstName());
        person.setLastName(signupDto.getLastName());
        person.setUsername(signupDto.getUsername());
        person.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        Person createdUser = personRepository.save(person);
        PersonDto personDto = new PersonDto();
        personDto.setPersonId(createdUser.getPersonId());
        personDto.setUsername(createdUser.getUsername());
        personDto.setFirstName(createdUser.getFirstName());
        personDto.setLastName(createdUser.getLastName());
        return personDto;
    }
}
