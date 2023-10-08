package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Revenue;
import mk.com.theagrodiarybackend.model.dto.RevenueDto;
import mk.com.theagrodiarybackend.service.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/edit/{id}")
    public ResponseEntity<Revenue> edit(@PathVariable Long id, @RequestBody RevenueDto revenueDto) {
        return this.revenueService.edit(id, revenueDto)
                .map(revenue -> ResponseEntity.ok().body(revenue))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.revenueService.delete(id);
    }
}
