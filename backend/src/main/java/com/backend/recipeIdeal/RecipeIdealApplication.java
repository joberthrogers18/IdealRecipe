package com.backend.recipeIdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecipeIdealApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipeIdealApplication.class, args);
	}
}
