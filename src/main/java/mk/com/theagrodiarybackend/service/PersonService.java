package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.dto.PersonDto;

import java.util.Optional;

public interface PersonService {

    Optional<Person> findByUsername();
    Optional<Person> editUserInfo(PersonDto personDto);
}
