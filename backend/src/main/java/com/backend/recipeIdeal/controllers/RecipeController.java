package com.backend.recipeIdeal.controllers;

import com.backend.recipeIdeal.dtos.RequestRecipeDTO;
import com.backend.recipeIdeal.dtos.ResponseRestDTO;
import com.backend.recipeIdeal.exceptions.EntityNotFoundException;
import com.backend.recipeIdeal.models.Recipe;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.recipeIdeal.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  @GetMapping("/")
  public ResponseEntity<ResponseRestDTO<Object>> getAllRecipes() {
    try {
      List<Recipe> recipes = recipeService.getAllRecipes();
      ResponseRestDTO<Object> responseBody = ResponseRestDTO.builder()
          .statusCode(HttpStatus.ACCEPTED.toString()).message("").data(recipes).build();
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
              .message(e.getMessage()).data(null).build());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseRestDTO<Object>> getRecipeById(@PathVariable("id") Long id) {
    try {
      Optional<Recipe> recipe = recipeService.getRecipeById(id);

      if (recipe.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ResponseRestDTO.builder().statusCode(HttpStatus.NOT_FOUND.toString())
                .message("Recipe not found").data(null).build());
      }

      return ResponseEntity.status(HttpStatus.ACCEPTED).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.ACCEPTED.toString()).message("")
              .data(recipe).build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
              .message(e.getMessage()).data(null).build());
    }
  }

  @PostMapping("/")
  public ResponseEntity<ResponseRestDTO<Object>> createRecipe(@RequestBody RequestRecipeDTO body) {
    try {
      recipeService.createRecipe(body);
      return ResponseEntity.status(HttpStatus.CREATED).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.CREATED.toString())
              .message("Recipe create successfully").data(null).build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
              .message(e.getMessage()).data(null).build());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseRestDTO<Object>> updateRecipe(@PathVariable("id") Long id,
      @RequestBody RequestRecipeDTO body) {
    try {
      recipeService.updateRecipe(id, body);

      return ResponseEntity.status(HttpStatus.ACCEPTED).body(
          ResponseRestDTO.builder().statusCode(HttpStatus.ACCEPTED.toString())
              .message("Recipe updated successfully").data(null).build()
      );
    } catch (Exception e) {
      if (e instanceof EntityNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ResponseRestDTO.builder().statusCode(HttpStatus.NOT_FOUND.toString())
                .message("Recipe is not found").data(null).build()
        );
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ResponseRestDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("It was not possible recover recipe!").data(e.getMessage()).build()
        );
      }
    }
  }

}
