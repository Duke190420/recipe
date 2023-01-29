package recipes.controllerLayer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import recipes.businessLayer.RecipeService;
import recipes.businessLayer.Recipe;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Data
@RestController
public class RecipeController {
    private long id = 1;
    @Autowired
    RecipeService recipeService;



    @PostMapping(value = "/api/recipe/new")
    public ResponseEntity<Object> postRecipe(@RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails details) {
        try {
            recipeService.postRecipe(recipe);
            id += 1;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Map.of("id", id-1), HttpStatus.OK);
    }

    @GetMapping(value = "/api/recipe/{id}")
    public ResponseEntity<Object> getRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails details){
        return recipeService.existRecipe(id) ? new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/recipe/{id}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails details){
        return recipeService.deleteRecipe(id) ? new ResponseEntity<>(id, HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/recipe/{id}")
    public ResponseEntity<Object> putRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails details) {
        if (id > this.id) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return recipeService.putRecipe(id, recipe) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/recipe/search/")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam(required = false) String category,
                                                      @RequestParam(required = false) String name, @AuthenticationPrincipal UserDetails details) {
        if (category != null && name != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (category != null) {
            return new ResponseEntity<>(recipeService.searchCategory(category), HttpStatus.OK);
        } else if (name != null) {
            return new ResponseEntity<>(recipeService.searchName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}