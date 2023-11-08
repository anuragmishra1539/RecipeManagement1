package com.recipe.recipemanagement.RecipeRepository;

import com.recipe.recipemanagement.RecipeModel.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
