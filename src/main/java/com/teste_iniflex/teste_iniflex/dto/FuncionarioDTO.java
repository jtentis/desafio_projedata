package com.teste_iniflex.teste_iniflex.dto;

import com.teste_iniflex.teste_iniflex.entity.Funcionario;
import com.teste_iniflex.teste_iniflex.utils.FormatadorUtil;
import lombok.Getter;

@Getter
public class FuncionarioDTO {
    private final String nome;
    private final String dataNascimento;
    private final String salario;
    private final String funcao;

    public FuncionarioDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.dataNascimento = FormatadorUtil.formatarData(funcionario.getDataNascimento());
        this.salario = FormatadorUtil.formatarMoeda(funcionario.getSalario());
        this.funcao = funcionario.getFuncao();
    }
}
