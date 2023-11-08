package com.recipe.recipemanagement.RecipeManagemenServices;

import com.recipe.recipemanagement.RecipeModel.AuthenticationToken;
import com.recipe.recipemanagement.RecipeModel.Dto.AuthInfo;
import com.recipe.recipemanagement.RecipeModel.Dto.SignInInput;
import com.recipe.recipemanagement.RecipeModel.Recipe;
import com.recipe.recipemanagement.RecipeModel.User;
import com.recipe.recipemanagement.RecipeRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
@Service
public class UserServices {

    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenService tokenService;
    public String userSignUp(User user) {
        String newEmail = user.getEmail();
        User existingUser = userRepo.findFirstByEmail(newEmail);

        if (existingUser != null) {
            return "email already in use";
        }
        String signUpPassword = user.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setPassword(encryptedPassword);

            userRepo.save(user);
            return "user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }
    public String userSignIn(SignInInput signInInput) {

        String email = signInInput.getEmail();

        User existingUser = userRepo.findFirstByEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        String password = signInInput.getPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            if(existingUser.getPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);
                    tokenService.createToken(token);
                    return "Sign in successful your token is "+ token.getTokenValue();

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }
    public String patientSignOut(AuthInfo authInfo) {

        if(tokenService.authenticate(authInfo)) {
            String tokenValue = authInfo.getTokenValue();
            tokenService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }

    }
    public List<Recipe> getYourPost( String email){
        return userRepo.findFirstByEmail(email).getRecipes();

    }
    public  ResponseEntity<List<Recipe>> getRecipeByEmail(String email){
        User users = userRepo.findFirstByEmail(email);
        if(users!=null){
            List<Recipe> recipes= getYourPost(email);
            return  ResponseEntity.of(Optional.of(recipes));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
