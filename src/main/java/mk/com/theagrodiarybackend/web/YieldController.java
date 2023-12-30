package mk.com.theagrodiarybackend.web;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Yield;
import mk.com.theagrodiarybackend.model.dto.YieldDto;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeed;
import mk.com.theagrodiarybackend.model.dto.YieldSummaryByYearAndSeedAndType;
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

    @GetMapping("/statistics1")
    public List<YieldSummaryByYearAndSeed> yieldSummaryByYearAndSeed () {
        return this.yieldService.yieldSummaryByYearAndSeed();
    }

    @GetMapping("/statistics2")
    public List<YieldSummaryByYearAndSeedAndType> yieldSummaryByYearAndSeedAndType () {
        return this.yieldService.yieldSummaryByYearAndSeedAndType();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Yield> listById(@PathVariable Integer id) {
        return this.yieldService.findById(id)
                .map(yield -> ResponseEntity.ok().body(yield))
                .orElseGet(() -> ResponseEntity.notFound().build());
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