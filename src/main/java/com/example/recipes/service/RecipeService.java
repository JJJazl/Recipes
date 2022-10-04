package com.example.recipes.service;

import com.example.recipes.entity.RecipeEntity;
import com.example.recipes.dto.RecipeDto;

import java.util.List;

public interface RecipeService {

    RecipeDto save(RecipeEntity entity);
    void updateById(RecipeEntity entity, long id);
    RecipeDto findById(long id);
    List<RecipeDto> findByName(String name);
    List<RecipeDto> findByCategory(String category);
    void deleteById(long id);

}
