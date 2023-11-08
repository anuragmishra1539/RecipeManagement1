package com.recipe.recipemanagement.RecipeManagemenServices;

import com.recipe.recipemanagement.RecipeModel.AuthenticationToken;
import com.recipe.recipemanagement.RecipeModel.Dto.AuthInfo;
import com.recipe.recipemanagement.RecipeModel.Recipe;
import com.recipe.recipemanagement.RecipeRepository.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    TokenRepo tokenRepo;


    public void createToken(AuthenticationToken token) {
        tokenRepo.save(token);
    }
    public void deleteToken(String tokenValue) {
        AuthenticationToken token =  tokenRepo.findFirstByTokenValue(tokenValue);
        tokenRepo.delete(token);
    }

    public boolean authenticate(AuthInfo authInfo) {

        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        //find thr actual token -> get the connected patient -> get its email-> verify with passed email

        //return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        AuthenticationToken token = tokenRepo.findFirstByTokenValue(tokenValue);
        if (token != null) {
            return token.getUser().getEmail().equals(email);
        } else {
            return false;
        }
    }

}
