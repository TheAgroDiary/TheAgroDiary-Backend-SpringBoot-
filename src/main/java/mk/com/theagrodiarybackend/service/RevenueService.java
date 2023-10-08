package mk.com.theagrodiarybackend.service;


import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;

import java.util.List;
import java.util.Optional;

public interface RevenueService {

    List<Revenue> findAll();
    Optional<Revenue> findById(Long revenueId);
    Optional<Revenue> save(RevenueDto revenueDto);
    Optional<Revenue> edit(Long revenueId, RevenueDto revenueDto);
    void delete(Long revenueId);
}
