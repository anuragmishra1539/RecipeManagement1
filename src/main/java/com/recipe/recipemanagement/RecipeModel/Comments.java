package com.recipe.recipemanagement.RecipeModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private  int commentId;
    @NotNull
    private String comment;
    @ManyToOne
    private User user;
}
