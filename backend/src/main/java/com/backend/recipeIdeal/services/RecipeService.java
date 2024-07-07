package com.backend.recipeIdeal.services;

import com.backend.recipeIdeal.dtos.RequestRecipeDTO;
import com.backend.recipeIdeal.models.Recipe;
import com.backend.recipeIdeal.repositories.RecipeRepository;
import com.backend.recipeIdeal.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public void createRecipe(RequestRecipeDTO recipeDto) {
    Recipe recipe = Recipe.builder().title(recipeDto.getTitle()).description(recipeDto.getDescription()).build();
    recipeRepository.save(recipe);
  }

  public Recipe updateRecipe(Long id, RequestRecipeDTO recipe) {
    Recipe currentRecipe = getRecipeById(id).orElseThrow(
        () -> new EntityNotFoundException("Recipe not found with id: " + id)
    );
    currentRecipe.setTitle(recipe.getTitle());
    currentRecipe.setDescription(recipe.getDescription());
    return recipeRepository.save(currentRecipe);
  }

  public void deleteRecipe(Long id) {
    Recipe currentRecipe = getRecipeById(id).orElseThrow(
        () -> new EntityNotFoundException("Recipe not found with id: " + id));
    recipeRepository.deleteById(id);
  }

}
