package com.teste_iniflex.teste_iniflex.service;

import com.teste_iniflex.teste_iniflex.dto.FuncionarioMaisVelhoDTO;
import com.teste_iniflex.teste_iniflex.entity.Funcionario;
import com.teste_iniflex.teste_iniflex.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    public void removerPorNome(String nome) {
        repository.findAll().stream()
                .filter(f -> f.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .ifPresent(repository::delete);
    }

    public void aumentarSalario(double percentual) {
        List<Funcionario> funcionarios = repository.findAll();
        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1 + percentual / 100)))
        );
        repository.saveAll(funcionarios);
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> listarPorMesNascimento(Month... meses) {
        Set<Month> mesesSet = new HashSet<>(Arrays.asList(meses));
        return repository.findAll().stream()
                .filter(f -> mesesSet.contains(f.getDataNascimento().getMonth()))
                .toList();
    }

    public FuncionarioMaisVelhoDTO obterFuncionarioMaisVelho() {
        Optional<Funcionario> funcionarioMaisVelho = repository.findAll().stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));

        return funcionarioMaisVelho.map(FuncionarioMaisVelhoDTO::new).orElse(null);
    }

    public List<Funcionario> listarAniversariantesOutubroDezembro() {
        return repository.findAll().stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER ||
                        f.getDataNascimento().getMonth() == Month.DECEMBER)
                .collect(Collectors.toList());
    }

    public List<Funcionario> ordenarPorNome() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();
    }

    public BigDecimal calcularTotalSalarios() {
        return repository.findAll().stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> calcularSalariosMinimos() {
        return repository.findAll().stream()
                .collect(Collectors.toMap(
                        Funcionario::getNome,
                        f -> f.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP)
                ));
    }
}
