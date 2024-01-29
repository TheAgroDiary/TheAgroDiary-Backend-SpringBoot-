package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.dto.PersonDto;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public Optional<Person> findByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " + username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return Optional.of(this.personRepository.findByPersonId(personId))
                    .orElseThrow(() -> new UserNotFoundException(personId));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Person> editUserInfo(PersonDto personDto) {
        Optional<Person> person = findByUsername();

        if (person.isPresent()) {
            if (!person.get().getFirstName().equals(personDto.getFirstName())) {
                person.get().setFirstName(personDto.getFirstName());
                System.out.println("First name is different!");
                System.out.println(person.get().getFirstName());
            }
            if (!person.get().getLastName().equals(personDto.getLastName())) {
                person.get().setLastName(personDto.getLastName());
                System.out.println("Last name is different");
            }
            if ((!personDto.getPassword().equals(""))) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (!encoder.matches(personDto.getPassword(), person.get().getPassword())) {
                    if (!personDto.getPassword().equals(person.get().getPassword())) {
                        person.get().setPassword(new BCryptPasswordEncoder().encode(personDto.getPassword()));
                        System.out.println("Password is edited");
                        System.out.println(personDto.getPassword());
                    }
                }
            }

            this.personRepository.save(person.get());
            return Optional.of(person.get());
        }
        return Optional.empty();
    }
}
