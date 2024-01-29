package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.dto.PersonDto;
import mk.com.theagrodiarybackend.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/info")
    public ResponseEntity<Person> getPerson() {
        return this.personService.findByUsername()
                .map(person -> ResponseEntity.ok().body(person))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/editUser")
    public ResponseEntity<Person> editUserInfo(@RequestBody PersonDto personDto) {
        return this.personService.editUserInfo(personDto)
                .map(person -> ResponseEntity.ok().body(person))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
