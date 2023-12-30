package mk.com.theagrodiarybackend.service;


import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;
import mk.com.theagrodiarybackend.model.dto.RevenueSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.TotalRevenueSummaryByYear;

import java.util.List;
import java.util.Optional;

public interface RevenueService {

    List<Revenue> findAll();
    List<Revenue> findAllByPerson();
    Optional<Revenue> findById(Integer revenueId);
    List<RevenueSummaryByYearAndSeed> revenueSummaryByYearAndSeed();
    List<TotalRevenueSummaryByYear> totalRevenueByYear();
    Optional<Revenue> save(RevenueDto revenueDto);
    Optional<Revenue> edit(Integer revenueId, RevenueDto revenueDto);
    void delete(Integer revenueId);
}
