package mk.com.theagrodiarybackend.web;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;
import mk.com.theagrodiarybackend.service.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9091")
@RequestMapping(path = "/api/revenue")
@AllArgsConstructor
public class RevenueController {

    private RevenueService revenueService;

    @PostMapping("/add")
    public ResponseEntity<Revenue> save(@RequestBody RevenueDto revenueDto) {
        return this.revenueService.save(revenueDto)
                .map(revenue -> ResponseEntity.ok().body(revenue))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/my")
    public List<Revenue> listAllByPerson (){
        return this.revenueService.findAllByPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenue> listById(@PathVariable Integer id) {
        return this.revenueService.findById(id)
                .map(revenue -> ResponseEntity.ok().body(revenue))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Revenue> edit(@PathVariable Integer id, @RequestBody RevenueDto revenueDto) {
        return this.revenueService.edit(id, revenueDto)
                .map(revenue -> ResponseEntity.ok().body(revenue))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        this.revenueService.delete(id);
    }
}
