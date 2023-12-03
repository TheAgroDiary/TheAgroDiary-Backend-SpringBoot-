package mk.com.theagrodiarybackend.web;


import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Category;
import mk.com.theagrodiarybackend.model.dto.CategoryDto;
import mk.com.theagrodiarybackend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:3000"})
@RequestMapping(path = "/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> listSeeds() {
        return this.categoryService.findAll();
    }
    @PostMapping("/add")
    public ResponseEntity<Category> save(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.save(categoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Integer id) {
        return this.categoryService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return this.categoryService.edit(id, categoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
