package com.webclient.bas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "ONLINE");
        response.put("message", "API de Spring Security con JWT funcionando correctamente");
        response.put("swagger_ui", "https://ej-09.onrender.com/swagger-ui/index.html");
        response.put("endpoints_publicos", List.of(
                "POST /auth/signup (Registro de usuario)",
                "POST /auth/login (Inicio de sesión y obtención del Token JWT)"
        ));
        response.put("endpoints_protegidos", List.of(
                "GET /users/me (Requiere Header Authorization: Bearer <token>)",
                "GET /users (Requiere Header Authorization: Bearer <token>)"
        ));
        return ResponseEntity.ok(response);
    }
}
