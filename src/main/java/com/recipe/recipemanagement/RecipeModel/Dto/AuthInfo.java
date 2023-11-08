package com.recipe.recipemanagement.RecipeModel.Dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfo {

    @Email
    private String email;

    private String tokenValue;

}
