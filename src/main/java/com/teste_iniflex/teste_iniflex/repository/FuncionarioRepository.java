package com.teste_iniflex.teste_iniflex.repository;

import com.teste_iniflex.teste_iniflex.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByFuncao(String funcao);
}
