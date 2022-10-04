package com.example.recipes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.recipes.entity.UserEntity;
import com.example.recipes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void save(UserEntity user) {
        if (userRepo.findByEmailIgnoreCase(user.getEmail()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
            userRepo.save(user);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
