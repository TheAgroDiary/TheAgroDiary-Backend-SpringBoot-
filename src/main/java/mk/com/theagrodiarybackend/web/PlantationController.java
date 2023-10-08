package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.service.PlantationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:9091")
@RequestMapping(path = "/api/plantation")
@AllArgsConstructor
public class PlantationController {

    private PlantationService plantationService;

    @PostMapping("/add")
    public ResponseEntity<Plantation> save(@RequestBody PlantationDto plantationDto) {
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
