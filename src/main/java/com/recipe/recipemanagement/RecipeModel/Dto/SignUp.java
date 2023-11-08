package com.recipe.recipemanagement.RecipeModel.Dto;

import com.recipe.recipemanagement.RecipeModel.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    private String userName;
    private String password;
    private  String email;
    public User to(SignUp signUp){

         User user= new User() ;
                    user.setUserName(signUp.getUserName());
                    user.setEmail(signUp.getEmail());
                    user.setPassword(signUp.getPassword());
                return user;

    }
}
