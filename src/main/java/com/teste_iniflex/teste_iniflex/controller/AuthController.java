package com.teste_iniflex.teste_iniflex.controller;

import com.teste_iniflex.teste_iniflex.dto.AuthDTO;
import com.teste_iniflex.teste_iniflex.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthDTO request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO request) {
        return service.login(request);
    }
}
