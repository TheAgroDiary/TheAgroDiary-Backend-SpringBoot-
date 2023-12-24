package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.PlantationSummaryByYearAndSeedAndType;
import mk.com.theagrodiarybackend.service.PlantationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/plantation")
@AllArgsConstructor
public class PlantationController {

    private PlantationService plantationService;

    @GetMapping("/all")
    public List<Plantation> listAll(){
        return this.plantationService.findAll();
    }

    @GetMapping("/my")
    public List<Plantation> listAllByPerson (){
        return this.plantationService.findAllByPerson();
    }

    @GetMapping("/statistics1")
    public List<PlantationSummaryByYearAndSeed> plantationSummaryByYearAndSeed () {
        return this.plantationService.plantationSummaryByYearAndSeed();
    }

    @GetMapping("/statistics2")
    public List<PlantationSummaryByYearAndSeedAndType> plantationSummaryByYearAndSeedAndType () {
        return this.plantationService.plantationSummaryByYearAndSeedAndType();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plantation> listById(@PathVariable Integer id) {
        return this.plantationService.findById(id)
                .map(plantation -> ResponseEntity.ok().body(plantation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Plantation> save(@RequestBody PlantationDto plantationDto, Authentication authentication) {
        return this.plantationService.save(plantationDto)
                .map(plantation -> ResponseEntity.ok().body(plantation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Plantation> edit(@PathVariable Integer id, @RequestBody PlantationDto plantationDto) {
        return this.plantationService.edit(id, plantationDto)
                .map(plantation -> ResponseEntity.ok().body(plantation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.plantationService.delete(id);
    }
}
