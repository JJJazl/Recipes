package com.example.recipes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = ".+@.+\\..+")
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<RecipeEntity> recipes;
}
