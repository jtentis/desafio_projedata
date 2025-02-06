package com.teste_iniflex.teste_iniflex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
}
