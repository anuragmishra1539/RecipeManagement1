package com.recipe.recipemanagement.RecipeManagemenServices;

import com.recipe.recipemanagement.RecipeModel.Dto.AuthInfo;
import com.recipe.recipemanagement.RecipeModel.Dto.PostRecipeDto;
import com.recipe.recipemanagement.RecipeModel.Recipe;
import com.recipe.recipemanagement.RecipeModel.User;
import com.recipe.recipemanagement.RecipeRepository.RecipeRepo;
import com.recipe.recipemanagement.RecipeRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecipeServices {
    @Autowired
    RecipeRepo recipeRepo;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepo userRepo;

    public List<Recipe> getAllRecipe(AuthInfo authInfo) {
        if(tokenService.authenticate(authInfo)) {
            return recipeRepo.findAll();
        }
        else {
            return null;
        }
    }
    public  String postRecipe(PostRecipeDto postRecipeDto){
        Recipe recipe= new Recipe();
        recipe.setIngredients(postRecipeDto.getIngredients());
        recipe.setInstructions(postRecipeDto.getInstructions());
        recipe.setUser(userRepo.findFirstByEmail(postRecipeDto.getAuthInfo().getEmail()));
        recipeRepo.save(recipe);
        return "thanks for posting";
    }
    public  Recipe getRecipeByID(Integer recipeID){
        return recipeRepo.getReferenceById(recipeID);
    }
    public  String deleteRecipe( Recipe recipe){
        recipeRepo.delete(recipe);
        return recipe.getRecipeId()+"deleted";
    }
    public String updateRecipe( Recipe updatedRecipe, String email) {
        Recipe existingRecipe = recipeRepo.findById(updatedRecipe.getRecipeId()).orElse(null);

        if (existingRecipe != null && existingRecipe.getUser().equals(userRepo.findFirstByEmail(email))) {

            existingRecipe.setIngredients(updatedRecipe.getIngredients());
            existingRecipe.setInstructions(updatedRecipe.getInstructions());

            recipeRepo.save(existingRecipe);

            return "Recipe updated successfully";
        } else {
            throw new IllegalStateException("You are not authorized to update this recipe");
        }
    }
}
