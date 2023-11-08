package com.recipe.recipemanagement.RecipeModel.Dto;

import com.recipe.recipemanagement.RecipeModel.Recipe;
import com.recipe.recipemanagement.RecipeModel.User;
import com.recipe.recipemanagement.RecipeRepository.UserRepo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRecipeDto {

    @NotNull
    private  String ingredients;
    @NotNull
    private  String instructions;
    private AuthInfo authInfo;
}
