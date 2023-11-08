package com.recipe.recipemanagement.RecipeRepository;

import com.recipe.recipemanagement.RecipeModel.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comments,Integer> {
}
