package com.example.recipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.recipes.entity.RecipeEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
    @JsonIgnore
    private long id;
    private String name;
    private String category;
    private LocalDateTime date;
    private String description;
    private List<String> ingredients;
    private List<String> directions;

    public static RecipeDto toDto(RecipeEntity entity) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(entity.getId());
        recipeDto.setName(entity.getName());
        recipeDto.setCategory(entity.getCategory());
        recipeDto.setDate(entity.getDate());
        recipeDto.setDescription(entity.getDescription());
        recipeDto.setIngredients(entity.getIngredients());
        recipeDto.setDirections(entity.getDirections());
        return recipeDto;
    }
}
