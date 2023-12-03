package mk.com.theagrodiarybackend.repository;

import mk.com.theagrodiarybackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.categoryId = :categoryId")
    Optional<Category> findByCategoryId(Integer categoryId);

    @Query("select c from Category c where c.categoryName = :categoryName")
    Optional<Category> findByCategoryName (String categoryName);
}
