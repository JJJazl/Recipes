package com.example.recipes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.recipes.entity.UserEntity;
import com.example.recipes.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //БЕЗ АВТОРИЗАЦИИ(ТОЛЬКО ТЕЛО ЗАПРОСА)
    @PostMapping("/api/register")
    public void register(@Valid @RequestBody UserEntity user) {
        userService.save(user);
    }
}