package mk.com.theagrodiarybackend.web;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Plantation;
import mk.com.theagrodiarybackend.model.Yield;
import mk.com.theagrodiarybackend.model.dto.PlantationDto;
import mk.com.theagrodiarybackend.model.dto.YieldDto;
import mk.com.theagrodiarybackend.service.PlantationService;
import mk.com.theagrodiarybackend.service.YieldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/yield")
@AllArgsConstructor
public class YieldController {

    private YieldService yieldService;

    @PostMapping("/add")
    public ResponseEntity<Yield> save(@RequestBody YieldDto yieldDto) {
        return this.yieldService.save(yieldDto)
                .map(yield -> ResponseEntity.ok().body(yield))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/my")
    public List<Yield> listAllByPerson (){
        return this.yieldService.findAllByPerson();
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Yield> edit(@PathVariable Integer id, @RequestBody YieldDto yieldDto) {
        return this.yieldService.edit(id, yieldDto)
                .map(yield -> ResponseEntity.ok().body(yield))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.yieldService.delete(id);
    }
}