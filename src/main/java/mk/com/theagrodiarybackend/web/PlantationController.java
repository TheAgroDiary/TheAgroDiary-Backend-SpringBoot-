package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
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
    @PostMapping("/add")
    public ResponseEntity<Plantation> save(@RequestBody PlantationDto plantationDto, Authentication authentication) {
        String username = authentication.getName();
        plantationDto.setPersonId(14L);
//        plantationDto.setSeedId(3L);
        return this.plantationService.save(plantationDto)
                .map(plantation -> ResponseEntity.ok().body(plantation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Plantation> edit(@PathVariable Long id, @RequestBody PlantationDto plantationDto) {
        return this.plantationService.edit(id, plantationDto)
                .map(plantation -> ResponseEntity.ok().body(plantation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.plantationService.delete(id);
    }
}
