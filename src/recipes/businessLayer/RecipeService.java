package recipes.businessLayer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import recipes.serviceLayer.RecipeRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RecipeService{

    private final RecipeRepository recipeRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void postRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe getRecipe(long id) {
        return recipeRepository.getById(id);
    }

    public boolean existRecipe(long id) {
        return recipeRepository.existsById(id);
    }

    public boolean deleteRecipe(long id) {
        if(recipeRepository.existsById(id)){
            recipeRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean putRecipe(long id, @RequestBody Recipe newRecipe){
        if(recipeRepository.existsById(id)){
            Recipe existingRecipe = recipeRepository.findById(id).get();
            existingRecipe.setName(newRecipe.getName());
            existingRecipe.setDescription(newRecipe.getDescription());
            existingRecipe.setIngredients(newRecipe.getIngredients());
            existingRecipe.setDirections(newRecipe.getDirections());
            recipeRepository.save(existingRecipe);
            return true;
        }
        else{
            return false;
        }
    }

    public List<Recipe> searchCategory(@RequestParam String category) {
        return recipeRepository.findByCategoryIgnoreCaseEqualsOrderByDateDesc(category);
    }

    public List<Recipe> searchName(@RequestParam String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    public boolean returnStupid(){
        return true;
    }

    public boolean returnSmart() { return false; }
}