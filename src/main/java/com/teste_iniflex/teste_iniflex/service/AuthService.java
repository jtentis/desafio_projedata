package com.teste_iniflex.teste_iniflex.service;

import com.teste_iniflex.teste_iniflex.dto.AuthDTO;
import com.teste_iniflex.teste_iniflex.entity.Usuario;
import com.teste_iniflex.teste_iniflex.repository.UsuarioRepository;
import com.teste_iniflex.teste_iniflex.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String register(AuthDTO request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRole("USER");
        repository.save(usuario);
        return "Usuário registrado com sucesso!";
    }

    public String login(AuthDTO request) {
        Optional<Usuario> user = repository.findByUsername(request.getUsername());

        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return jwtUtil.generateToken(user.get().getUsername());
        }
        throw new RuntimeException("Usuário ou senha inválida!");
    }
}
