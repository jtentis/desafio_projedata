package com.teste_iniflex.teste_iniflex.dto;
import com.teste_iniflex.teste_iniflex.entity.Funcionario;
import com.teste_iniflex.teste_iniflex.utils.FormatadorUtil;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class FuncionarioMaisVelhoDTO {
    private final String nome;
    private final String dataNascimento;
    private final int idade;

    public FuncionarioMaisVelhoDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.dataNascimento = FormatadorUtil.formatarData(funcionario.getDataNascimento());
        this.idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
    }
}
