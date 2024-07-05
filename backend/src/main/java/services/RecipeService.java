package services;

import com.backend.recipeIdeal.models.Recipe;
import com.backend.recipeIdeal.repositories.RecipeRepository;
import exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe recipe) {
        Recipe currentRecipe = getRecipeById(id).orElseThrow(
                () -> new EntityNotFoundException("Recipe not found with id: " + id)
        );
        currentRecipe.setTitle(recipe.getTitle());
        currentRecipe.setDescription(recipe.getDescription());
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        Recipe currentRecipe = getRecipeById(id).orElseThrow(() -> new EntityNotFoundException("Recipe not found with id: " + id));
        recipeRepository.deleteById(id);
    }

}
