package com.example.recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.recipes.entity.RecipeEntity;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    List<RecipeEntity> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
    List<RecipeEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);

}
