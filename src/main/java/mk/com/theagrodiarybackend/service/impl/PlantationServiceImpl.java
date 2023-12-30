package mk.com.theagrodiarybackend.service.impl;

import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeedAndType;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public List<Plantation> findAllByPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = null;
        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " + username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return this.plantationRepository.findAllByPerson(personId);
        }
        return null;
    }

    @Override
    public List<PlantationSummaryByYearAndSeed> plantationSummaryByYearAndSeed() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " + username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));

            List<Tuple> tuples = this.plantationRepository.sumPlantationsByYearAndSeed(personId);

            return tuples.stream().map(tuple -> {
                Integer year = tuple.get(0, Integer.class);
                String seedName = tuple.get(1, String.class);
                Double totalAmountKg = tuple.get(2, Double.class);

                return new PlantationSummaryByYearAndSeed(year, seedName, totalAmountKg);
            }).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    @Override
    public List<PlantationSummaryByYearAndSeedAndType> plantationSummaryByYearAndSeedAndType() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " + username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return this.plantationRepository.sumPlantationsByYearAndSeedAAndType(personId);
        }
        else {
            return null;
        }
    }

    @Override
    public Optional<Plantation> findById(Integer plantationId) {
        return Optional.of(this.plantationRepository.findByPlantationId(plantationId))
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
    }

    @Override
    public Optional<Plantation> save(PlantationDto plantationDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = null;
        Integer seedId = plantationDto.getSeedId();
        Seed seed = this.seedRepository.findBySeedId(plantationDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(seedId));

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " +username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            plantationDto.setPersonId(personId);
            System.out.println("Person id is " +personId);
            person = this.personRepository.findByPersonId(personId)
                    .orElseThrow(() -> new UserNotFoundException(personId));
        }
        else {
            System.out.println("Could not retrieve information for authenticated user!");
            person = this.personRepository.findByPersonId(plantationDto.getPersonId())
                    .orElseThrow(() -> new UserNotFoundException(plantationDto.getPersonId()));
        }
        plantationDto.setUpdatedAt(new Date());
        Plantation plantation = new Plantation(
                plantationDto.getAmountKg(), plantationDto.getType(), plantationDto.getYear(), plantationDto.getUpdatedAt(), person, seed);
        this.plantationRepository.save(plantation);
        return Optional.of(plantation);
    }

    @Override
    public Optional<Plantation> edit(Integer plantationId, PlantationDto plantationDto) {
        Plantation plantation = this.plantationRepository.findByPlantationId(plantationId)
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
        Seed seed = this.seedRepository.findBySeedId(plantationDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(plantationDto.getSeedId()));
        plantation.setAmountKg(plantationDto.getAmountKg());
        plantation.setType(plantationDto.getType());
        plantation.setYear(plantationDto.getYear());
        plantation.setUpdatedAt(new Date());
        plantation.setSeed(seed);
        this.plantationRepository.save(plantation);
        return Optional.of(plantation);
    }

    @Override
    public void delete(Integer plantationId) {
        Plantation plantation = this.plantationRepository.findByPlantationId(plantationId)
                .orElseThrow(() -> new PlantationNotFoundException(plantationId));
        this.plantationRepository.delete(plantation);
    }
}
