package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;

import java.util.List;
import java.util.Optional;

public interface PlantationService {

    List<Plantation> findAll();
    Optional<Plantation> findById(Long plantationId);
    Optional<Plantation> save(PlantationDto plantationDto);
    Optional<Plantation> edit(Long plantationId, PlantationDto plantationDto);
    void delete(Long plantationId);
}
