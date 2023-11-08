package com.recipe.recipemanagement.RecipeMController;

import com.recipe.recipemanagement.RecipeManagemenServices.TokenService;
import com.recipe.recipemanagement.RecipeManagemenServices.UserServices;
import com.recipe.recipemanagement.RecipeModel.Dto.AuthInfo;
import com.recipe.recipemanagement.RecipeModel.Dto.SignInInput;
import com.recipe.recipemanagement.RecipeModel.Dto.SignUp;
import com.recipe.recipemanagement.RecipeModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    TokenService tokenService;

    @PostMapping("/SignUp")
    public String SignUp(@RequestBody SignUp signUp ){
        User user = signUp.to(signUp);

        return userServices.userSignUp(user);
    }
    @PutMapping("/SignIn")
    public  String SignIn(@RequestBody  SignInInput signInInput){
        return userServices.userSignIn(signInInput);
    }

    @GetMapping ("/Auth")
    public  String auth(@RequestBody AuthInfo authInfo){
        if (tokenService.authenticate(authInfo)){
            return "authenticated";
        }
        else return  "not";
    }

}
