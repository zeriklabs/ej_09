package com.webclient.bas.controller;

import com.webclient.bas.entity.User;
import com.webclient.bas.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Endpoint protegido para consultar el usuario autenticado actualmente
     */
    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    /**
     * Endpoint protegido para listar todos los usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}

