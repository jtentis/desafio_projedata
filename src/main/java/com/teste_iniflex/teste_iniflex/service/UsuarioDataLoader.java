package com.teste_iniflex.teste_iniflex.service;

import com.teste_iniflex.teste_iniflex.entity.Usuario;
import com.teste_iniflex.teste_iniflex.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDataLoader implements CommandLineRunner {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioDataLoader(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) { // só cria usuário se o banco estiver vazio
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            repository.save(admin);
            System.out.println("Usuário ADMIN criado com sucesso!");
        }
    }
}
