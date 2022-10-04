package com.example.recipes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.recipes.entity.RecipeEntity;
import com.example.recipes.dto.RecipeDto;
import com.example.recipes.service.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
@Validated
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> addRecipe(@Valid @RequestBody RecipeEntity entity) {
        long id = recipeService.save(entity).getId();
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable long id) {
        RecipeDto recipeDto = recipeService.findById(id);
        return new ResponseEntity<>(recipeDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeDto>> getRecipeListByCategory(@RequestParam(required = false) String category,
                                                                   @RequestParam(required = false) String name) {
        if ((category == null && name == null) || (category != null && name != null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (category != null) {
            return new ResponseEntity<>(recipeService.findByCategory(category), HttpStatus.OK);
        } else
            return new ResponseEntity<>(recipeService.findByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipeById(@Min(1) @PathVariable long id,
                                 @Valid @RequestBody RecipeEntity entity) {
        recipeService.updateById(entity, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipeById(@Min(1) @PathVariable long id) {
        recipeService.deleteById(id);
    }

}