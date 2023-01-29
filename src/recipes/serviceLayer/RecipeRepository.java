package recipes.serviceLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;
import recipes.businessLayer.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(String category);
    List<Recipe> findByNameContaining(String name);
    List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(@RequestParam String name);
    List<Recipe> findByCategoryIgnoreCaseEqualsOrderByDateDesc(@RequestParam String category);
}