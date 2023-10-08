package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.model.exception.PlantationNotFoundException;
import mk.com.theagrodiarybackend.model.exception.SeedNotFoundException;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.repository.PlantationRepository;
import mk.com.theagrodiarybackend.repository.SeedRepository;
import mk.com.theagrodiarybackend.service.PlantationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PlantationServiceImpl implements PlantationService {

    private PlantationRepository plantationRepository;
    private PersonRepository personRepository;
    private SeedRepository seedRepository;


    @Override
    public List<Plantation> findAll() {
        return this.plantationRepository.findAll();
    }

    @Override
    public Optional<Plantation> findById(Long plantationId) {
        return Optional.of(this.plantationRepository.findByPlantationId(plantationId))
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
    }

    @Override
    public Optional<Plantation> save(PlantationDto plantationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = new Person();
        Seed seed = this.seedRepository.findBySeedId(plantationDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(plantationDto.getSeedId()));

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " +username);
            Long personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            System.out.println("Person id is " +personId);
            person = this.personRepository.findByPersonId(personId)
                    .orElseThrow(() -> new UserNotFoundException(personId));
        }
        else {
            System.out.println("Could not retrieve information for authenticated user!");
            person = this.personRepository.findByPersonId(plantationDto.getPersonId())
                    .orElseThrow(() -> new UserNotFoundException(plantationDto.getPersonId()));
        }

        Plantation plantation = new Plantation(
                plantationDto.getAmountKg(), plantationDto.getType(), plantationDto.getYear(), person, seed);
        this.plantationRepository.save(plantation);
        return Optional.of(plantation);
    }

    @Override
    public Optional<Plantation> edit(Long plantationId, PlantationDto plantationDto) {
        Plantation plantation = this.plantationRepository.findByPlantationId(plantationId)
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
        Seed seed = this.seedRepository.findBySeedId(plantationDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(plantationDto.getSeedId()));
        plantation.setAmountKg(plantationDto.getAmountKg());
        plantation.setType(plantationDto.getType());
        plantation.setYear(plantationDto.getYear());
        plantation.setSeed(seed);
        this.plantationRepository.save(plantation);
        return Optional.of(plantation);
    }

    @Override
    public void delete(Long plantationId) {
        Plantation plantation = this.plantationRepository.findByPlantationId(plantationId)
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
        this.plantationRepository.delete(plantation);
    }
}
