package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Person;
import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;
import mk.com.theagrodiarybackend.model.exception.RevenueNotFoundException;
import mk.com.theagrodiarybackend.model.exception.SeedNotFoundException;
import mk.com.theagrodiarybackend.model.exception.UserNotFoundException;
import mk.com.theagrodiarybackend.repository.PersonRepository;
import mk.com.theagrodiarybackend.repository.RevenueRepository;
import mk.com.theagrodiarybackend.repository.SeedRepository;
import mk.com.theagrodiarybackend.service.RevenueService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RevenueServiceImpl implements RevenueService {

    private RevenueRepository revenueRepository;
    private PersonRepository personRepository;
    private SeedRepository seedRepository;

    @Override
    public List<Revenue> findAll() {
        return this.revenueRepository.findAll();
    }

    @Override
    public List<Revenue> findAllByPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            System.out.println("Current user is: " + username);
            Integer personId = this.personRepository.getPersonIdByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return this.revenueRepository.findAllByPerson(personId);
        }
        return null;
    }

    @Override
    public Optional<Revenue> findById(Integer revenueId) {
        return Optional.of(this.revenueRepository.findByRevenueId(revenueId)
                .orElseThrow(() -> new RevenueNotFoundException(revenueId)));
    }

    @Override
    public Optional<Revenue> save(RevenueDto revenueDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = new Person();
        Seed seed = this.seedRepository.findBySeedId(revenueDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(revenueDto.getSeedId()));

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
            person = this.personRepository.findByPersonId(revenueDto.getPersonId())
                    .orElseThrow(() -> new UserNotFoundException(revenueDto.getPersonId()));
        }

        Revenue revenue = new Revenue(
                revenueDto.getRevenueSum(), revenueDto.getDate(), revenueDto.getSeedAmountKg(), person, seed);
        this.revenueRepository.save(revenue);
        return Optional.of(revenue);
    }

    @Override
    public Optional<Revenue> edit(Integer revenueId, RevenueDto revenueDto) {
        Revenue revenue = this.revenueRepository.findByRevenueId(revenueId)
                .orElseThrow(() -> new RevenueNotFoundException(revenueId));
        Seed seed = this.seedRepository.findBySeedId(revenueDto.getSeedId())
                .orElseThrow(() -> new SeedNotFoundException(revenueDto.getSeedId()));
        revenue.setRevenueSum(revenueDto.getRevenueSum());
        revenue.setDate(revenueDto.getDate());
        revenue.setSeedAmountKg(revenueDto.getSeedAmountKg());
        revenue.setSeed(seed);
        this.revenueRepository.save(revenue);
        return Optional.of(revenue);
    }

    @Override
    public void delete(Integer revenueId) {
        Revenue revenue = this.revenueRepository.findByRevenueId(revenueId)
                .orElseThrow(() -> new RevenueNotFoundException(revenueId));
        this.revenueRepository.delete(revenue);
    }
}
