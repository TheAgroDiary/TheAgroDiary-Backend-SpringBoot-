package mk.com.theagrodiarybackend.service.impl;

import lombok.AllArgsConstructor;
import mk.com.theagrodiarybackend.model.Category;
import mk.com.theagrodiarybackend.model.dto.CategoryDto;
import mk.com.theagrodiarybackend.model.exception.CategoryNameNotFoundException;
import mk.com.theagrodiarybackend.model.exception.CategoryNotFoundException;
import mk.com.theagrodiarybackend.repository.CategoryRepository;
import mk.com.theagrodiarybackend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {

        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {
        return Optional.of(this.categoryRepository.findByCategoryId(categoryId))
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    @Override
    public Optional<Category> findByName(String categoryName) {
        return Optional.of(this.categoryRepository.findByCategoryName(categoryName))
                .orElseThrow(() -> new CategoryNameNotFoundException(categoryName));
    }

    @Override
    public Optional<Category> save(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getCategoryName());
        this.categoryRepository.save(category);
        return Optional.of(category);
    }

    @Override
    public Optional<Category> edit(Integer categoryId, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        category.setCategoryName(categoryDto.getCategoryName());
        this.categoryRepository.save(category);
        return Optional.of(category);
    }
}
