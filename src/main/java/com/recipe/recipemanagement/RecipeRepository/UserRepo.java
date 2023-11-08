package com.recipe.recipemanagement.RecipeRepository;

import com.recipe.recipemanagement.RecipeModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
}
