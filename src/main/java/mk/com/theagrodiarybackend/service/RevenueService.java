package mk.com.theagrodiarybackend.service;


import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;

import java.util.List;
import java.util.Optional;

public interface RevenueService {

    List<Revenue> findAll();
    List<Revenue> findAllByPerson();
    Optional<Revenue> findById(Integer revenueId);
    Optional<Revenue> save(RevenueDto revenueDto);
    Optional<Revenue> edit(Integer revenueId, RevenueDto revenueDto);
    void delete(Integer revenueId);
}
