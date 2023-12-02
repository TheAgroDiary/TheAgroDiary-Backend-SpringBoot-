package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.Yield;
import mk.com.theagrodiarybackend.model.dto.YieldDto;
import mk.com.theagrodiarybackend.model.exception.SeedNotFoundException;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.model.exception.YieldNotFountException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.repository.SeedRepository;
import mk.com.theagrodiarybackend.repository.YieldRepository;
import mk.com.theagrodiarybackend.service.YieldService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class YieldServiceImpl implements YieldService {

    private YieldRepository yieldRepository;
    private PersonRepository personRepository;
    private SeedRepository seedRepository;

    @Override
    public List<Yield> findAll() {
        return this.yieldRepository.findAll();
    }

    @Override
    public Optional<Yield> findById(Integer yieldId) {
        return Optional.of(this.yieldRepository.findByYieldId(yieldId)
                .orElseThrow(() -> new YieldNotFountException(yieldId)));
    }

    @Override
    public Optional<Yield> save(YieldDto yieldDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = new Person();
        Seed seed = this.seedRepository.findBySeedId(yieldDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(yieldDto.getSeedId()));

        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " +username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            System.out.println("Person id is " +personId);
            person = this.personRepository.findByPersonId(personId)
                    .orElseThrow(() -> new UserNotFoundException(personId));
        }
        else {
            System.out.println("Could not retrieve information for authenticated user!");
            person = this.personRepository.findByPersonId(yieldDto.getPersonId())
                    .orElseThrow(() -> new UserNotFoundException(yieldDto.getPersonId()));
        }

        Yield yield = new Yield(
                yieldDto.getAmountKg(), yieldDto.getType(), yieldDto.getYear(), person, seed);
        this.yieldRepository.save(yield);
        return Optional.of(yield);
    }

    @Override
    public Optional<Yield> edit(Integer yieldId, YieldDto yieldDto) {
        Yield yield = this.yieldRepository.findByYieldId(yieldId)
                .orElseThrow(() -> new YieldNotFountException(yieldId));
        Seed seed = this.seedRepository.findBySeedId(yieldDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(yieldDto.getSeedId()));
        yield.setAmountKg(yieldDto.getAmountKg());
        yield.setType(yieldDto.getType());
        yield.setYear(yieldDto.getYear());
        yield.setSeed(seed);
        this.yieldRepository.save(yield);
        return Optional.of(yield);
    }

    @Override
    public void delete(Integer yieldId) {
        Yield yield = this.yieldRepository.findByYieldId(yieldId)
                .orElseThrow(() -> new YieldNotFountException(yieldId));
        this.yieldRepository.delete(yield);
    }
}
