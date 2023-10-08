package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.SeedDto;

import java.util.List;
import java.util.Optional;

public interface SeedService {

    List<Seed> findAll();
    Optional<Seed> findById(Long seedId);
    Optional<Seed> findByName(String seedName);
    Optional<Seed> save(SeedDto seedDto);
    Optional<Seed> edit(Long seedId, SeedDto seedDto);
//    void delete(Long seedId);
}
