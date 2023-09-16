package mk.com.theagrodiarybackend.service.jwt;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Write Logic to get the user from the DB
        Optional<Person> person = personRepository.findByUsername(username);
        if(person == null) {
            throw new UsernameNotFoundException("User not found", null);
        }
        return new org.springframework.security.core.userdetails.User(
                person.get().getUsername(),
                person.get().getPassword(),
                new ArrayList<>());
    }
}