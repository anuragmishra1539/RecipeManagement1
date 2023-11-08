package com.recipe.recipemanagement.RecipeMController;

import com.recipe.recipemanagement.RecipeManagemenServices.RecipeServices;
import com.recipe.recipemanagement.RecipeManagemenServices.TokenService;
import com.recipe.recipemanagement.RecipeManagemenServices.UserServices;
import com.recipe.recipemanagement.RecipeModel.Dto.AuthInfo;
import com.recipe.recipemanagement.RecipeModel.Dto.PostRecipeDto;
import com.recipe.recipemanagement.RecipeModel.Recipe;
import com.recipe.recipemanagement.RecipeRepository.RecipeRepo;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    RecipeServices recipeServices;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserServices userServices;
    @Autowired
    RecipeRepo recipeRepo;

    @PostMapping("/newPost")
    public String postRecipe(@RequestBody PostRecipeDto postRecipeDto ){
        if (tokenService.authenticate(postRecipeDto.getAuthInfo())) {
            return recipeServices.postRecipe(postRecipeDto);
        }
        else return "SignIn First";
    }
    @DeleteMapping("/deletepost/{recipeId}")
    public  String deletePost(@RequestBody @Valid AuthInfo authInfo,@PathParam("recipeId") int recipeId) {
        if (tokenService.authenticate(authInfo)) {
            Recipe deletedRecipe = recipeRepo.findFirstByRecipeId(recipeId);
            if (deletedRecipe != null) {
                if (deletedRecipe.getUser().getEmail().equals(authInfo.getEmail())) {
                  return  recipeServices.deleteRecipe(deletedRecipe);
                } else return "Sorry not your Post";

            } else return "Invalid RecipeId";
        }
        return "SignIn First";
    }
    @GetMapping("/YourRecipes")
    public ResponseEntity<List<Recipe>>  getRecipe(@RequestBody AuthInfo authInfo){

        if (tokenService.authenticate(authInfo)){
           return userServices.getRecipeByEmail(authInfo.getEmail());
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/Recipes/{authInfo}/{email}")
    public ResponseEntity<List<Recipe>>  getRecipebyUserId(@RequestParam @Valid AuthInfo authInfo, @RequestBody String email){
        if (tokenService.authenticate(authInfo)){
            return userServices.getRecipeByEmail(email);
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PutMapping("/RecipeUpdate/{authInfo}/{updatedRecipe}")
     public  String updateRecipe(@RequestBody @Valid AuthInfo authInfo,@RequestBody Recipe updatedRecipe){
        if (tokenService.authenticate(authInfo)){
            return  recipeServices.updateRecipe(updatedRecipe,authInfo.getEmail());
        }
        return "Please SignIn";
    }


}
