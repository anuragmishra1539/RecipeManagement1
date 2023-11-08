package com.recipe.recipemanagement.RecipeRepository;

import com.recipe.recipemanagement.RecipeModel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe,Integer> {
    Recipe findFirstByRecipeId(int id);
}
