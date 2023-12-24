package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeedAndType;

import java.util.List;
import java.util.Optional;

public interface PlantationService {

    List<Plantation> findAll();
    List<Plantation> findAllByPerson();
    List<PlantationSummaryByYearAndSeed> plantationSummaryByYearAndSeed();
    List<PlantationSummaryByYearAndSeedAndType> plantationSummaryByYearAndSeedAndType();
    Optional<Plantation> findById(Integer plantationId);
    Optional<Plantation> save(PlantationDto plantationDto);
    Optional<Plantation> edit(Integer plantationId, PlantationDto plantationDto);
    void delete(Integer plantationId);
}
