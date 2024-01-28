package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> findByUsername();
}
