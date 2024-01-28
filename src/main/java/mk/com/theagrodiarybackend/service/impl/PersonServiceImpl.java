package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.service.PersonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
}
