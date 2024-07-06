package com.backend.recipeIdeal.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRecipeDTO {

  @NotEmpty(message = "Title is required")
  private String title;

  @NotEmpty(message = "Description is required")
  private String description;

}
