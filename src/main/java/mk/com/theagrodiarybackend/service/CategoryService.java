package mk.com.theagrodiarybackend.service;

import mk.com.theagrodiarybackend.model.Category;
import mk.com.theagrodiarybackend.model.dto.CategoryDto;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();
    Optional<Category> findById(Integer categoryId);
    Optional<Category> findByName(String categoryName);
    Optional<Category> save(CategoryDto categoryDto);
    Optional<Category> edit(Integer categoryId, CategoryDto categoryDto);
}
