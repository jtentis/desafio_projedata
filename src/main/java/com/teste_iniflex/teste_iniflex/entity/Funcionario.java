package com.teste_iniflex.teste_iniflex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal salario;
    private String funcao;

    // Adicionando um construtor personalizado
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
}
