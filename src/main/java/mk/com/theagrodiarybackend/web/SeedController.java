package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Seed;
import mk.com.theagrodiarybackend.model.dto.SeedDto;
import mk.com.theagrodiarybackend.service.SeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/seed")
@AllArgsConstructor
public class SeedController {

    private SeedService seedService;

    @GetMapping("/seeds")
    public List<Seed> listSeeds() {
        return this.seedService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<Seed> save(@RequestBody SeedDto seedDto) {
        return this.seedService.save(seedDto)
                .map(seed -> ResponseEntity.ok().body(seed))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seed> getById(@PathVariable Long id) {
        return this.seedService.findById(id)
                .map(seed -> ResponseEntity.ok().body(seed))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Seed> edit(@PathVariable Long id, @RequestBody SeedDto seedDto) {
        return this.seedService.edit(id, seedDto)
                .map(seed -> ResponseEntity.ok().body(seed))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
