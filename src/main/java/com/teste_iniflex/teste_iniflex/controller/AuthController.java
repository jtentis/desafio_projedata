package com.teste_iniflex.teste_iniflex.controller;

import com.teste_iniflex.teste_iniflex.dto.AuthDTO;
import com.teste_iniflex.teste_iniflex.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Autenticação")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuário")
    public String register(@RequestBody AuthDTO request) {
        return service.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário")
    public String login(@RequestBody AuthDTO request) {
        return service.login(request);
    }
}
