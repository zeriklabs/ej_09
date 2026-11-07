package com.webclient.bas.controller;

import com.webclient.bas.dto.LoginResponse;
import com.webclient.bas.dto.LoginUserDto;
import com.webclient.bas.dto.RegisterUserDto;
import com.webclient.bas.entity.User;
import com.webclient.bas.service.AuthenticationService;
import com.webclient.bas.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/signup")
    public ResponseEntity<Map<String, String>> signupInfo() {
        return ResponseEntity.ok(Map.of(
                "mensaje", "Este es un endpoint REST. Para registrarte debes enviar una peticion HTTP POST con un body JSON.",
                "metodo_requerido", "POST",
                "ejemplo_body", "{\"fullName\":\"Tu Nombre\", \"email\":\"usuario@example.com\", \"password\":\"password123\"}"
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> loginInfo() {
        return ResponseEntity.ok(Map.of(
                "mensaje", "Este es un endpoint REST. Para iniciar sesion debes enviar una peticion HTTP POST con tu email y password en un body JSON.",
                "metodo_requerido", "POST",
                "ejemplo_body", "{\"email\":\"usuario@example.com\", \"password\":\"password123\"}"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
