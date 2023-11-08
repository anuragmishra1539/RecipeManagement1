package com.recipe.recipemanagement.RecipeModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property="userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  userId;
    private String userName;
    private String Password;
    @OneToMany(mappedBy = "user")
    private  List<Recipe> recipes;
    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    private  String email;

}
