package com.example.recipes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.recipes.entity.RecipeEntity;
import com.example.recipes.entity.UserEntity;
import com.example.recipes.dto.RecipeDto;
import com.example.recipes.repository.RecipeRepository;
import com.example.recipes.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;

    private final UserRepository userRepo;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepo, UserRepository userRepo) {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
    }

    private RecipeEntity findEntityById(long id) {
        return recipeRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private UserDetails getLoggedUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public RecipeDto save(RecipeEntity entity) {
        UserEntity user = userRepo.findByEmailIgnoreCase(getLoggedUser().getUsername()).orElseThrow();
        entity.setUser(user);
        return RecipeDto.toDto(recipeRepo.save(entity));
    }

    @Override
    public void updateById(RecipeEntity entity, long id) {
        RecipeEntity recipe = findEntityById(id);

        if (recipe.getUser().getEmail().equals(getLoggedUser().getUsername())) {
            recipe.setName(entity.getName());
            recipe.setCategory(entity.getCategory());
            recipe.setDescription(entity.getDescription());
            recipe.setDirections(entity.getDirections());
            recipe.setIngredients(entity.getIngredients());
            recipeRepo.save(recipe);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public RecipeDto findById(long id) {
        return RecipeDto.toDto(findEntityById(id));
    }

    @Override
    public List<RecipeDto> findByName(String name) {
        return recipeRepo.findByNameContainingIgnoreCaseOrderByDateDesc(name).stream()
                .map(RecipeDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> findByCategory(String category) {
        return recipeRepo.findByCategoryIgnoreCaseOrderByDateDesc(category).stream()
                .map(RecipeDto::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteById(long id) {
        RecipeEntity recipe = findEntityById(id);
        if (recipe.getUser().getEmail().equals(getLoggedUser().getUsername())) {
            recipeRepo.delete(recipe);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
