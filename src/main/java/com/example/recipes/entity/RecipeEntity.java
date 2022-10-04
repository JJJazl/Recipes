package com.example.recipes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;
    @UpdateTimestamp
    private LocalDateTime date = LocalDateTime.now();
    @NotBlank
    private String description;
    @ElementCollection
    //@CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @NotNull
    @Size(min = 1)
    private List<String> ingredients;
    @ElementCollection
    //@CollectionTable(name = "recipe_directions", joinColumns = @JoinColumn(name = "recipe_id"))
    @NotNull
    @Size(min = 1)
    private List<String> directions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public RecipeEntity(String name,
                        String category,
                        String description,
                        List<String> ingredients,
                        List<String> directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}
