package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.SeedDto;
import mk.com.theagrodiarybackend.model.exception.SeedNameNotFoundException;
import mk.com.theagrodiarybackend.model.exception.SeedNotFoundException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.repository.SeedRepository;
import mk.com.theagrodiarybackend.service.SeedService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SeedServiceImpl implements SeedService {

    private SeedRepository seedRepository;
    private PersonRepository personRepository;

    @Override
    public List<Seed> findAll() {
        return this.seedRepository.findAll();
    }

    @Override
    public Optional<Seed> findById(Integer seedId) {
        return Optional.of(this.seedRepository.findBySeedId(seedId))
                .orElseThrow(() -> new SeedNotFoundException(seedId));
    }

    @Override
    public Optional<Seed> findByName(String seedName) {
        return Optional.of(this.seedRepository.findBySeedName(seedName))
                .orElseThrow(() -> new SeedNameNotFoundException(seedName));
    }

    @Override
    public Optional<Seed> save(SeedDto seedDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        Person person = new Person();

//        if(authentication != null && authentication.isAuthenticated()) {
//            String username = authentication.getName();
//            System.out.println("Current user is: " +username);
//            Integer personId = this.personRepository.getPersonIdByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(username));
//            System.out.println("Person id is " +personId);
//            person = this.personRepository.findByPersonId(personId)
//                    .orElseThrow(() -> new UserNotFoundException(personId));
//        }
//        else {
//            System.out.println("Could not retrieve information for authenticated user!");
//            person = this.personRepository.findByPersonId(seedDto.getPerson())
//                    .orElseThrow(() -> new UserNotFoundException(seedDto.getPerson()));
//        }
        Seed seed = new Seed(seedDto.getSeedName());
        this.seedRepository.save(seed);
        return Optional.of(seed);
    }

    @Override
    public Optional<Seed> edit(Integer seedId, SeedDto seedDto) {
        Seed seed = this.seedRepository.findBySeedId(seedId)
                .orElseThrow(() -> new SeedNotFoundException(seedId));
        if (seed != null) {
            System.out.println("Seed is: " + seed.getSeedName());
        }
        seed.setSeedName(seedDto.getSeedName());
        this.seedRepository.save(seed);
        return Optional.of(seed);
    }
}
