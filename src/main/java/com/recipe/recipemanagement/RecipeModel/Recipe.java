package com.recipe.recipemanagement.RecipeModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="userId")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int recipeId;
    private  String ingredients;
    private  String instructions;
    @ManyToOne
    private User user;
}
