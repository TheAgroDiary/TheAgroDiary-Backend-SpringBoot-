package mk.com.theagrodiarybackend.service;


import mk.com.theagrodiarybackend.model.Yield;
import mk.com.theagrodiarybackend.model.dto.YieldDto;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeedAndType;

import java.util.List;
import java.util.Optional;

public interface YieldService {

    List<Yield> findAll();
    List<Yield> findAllByPerson();
    List<YieldSummaryByYearAndSeed> yieldSummaryByYearAndSeed();
    List<YieldSummaryByYearAndSeedAndType> yieldSummaryByYearAndSeedAndType();
    Optional<Yield> findById(Integer yieldId);
    Optional<Yield> save(YieldDto yieldDto);
    Optional<Yield> edit(Integer yieldId, YieldDto yieldDto);
    void delete(Integer yieldId);
}
