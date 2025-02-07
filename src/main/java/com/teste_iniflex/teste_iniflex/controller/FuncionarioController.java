package com.teste_iniflex.teste_iniflex.controller;

import com.teste_iniflex.teste_iniflex.dto.FuncionarioDTO;
import com.teste_iniflex.teste_iniflex.dto.FuncionarioMaisVelhoDTO;
import com.teste_iniflex.teste_iniflex.entity.Funcionario;
import com.teste_iniflex.teste_iniflex.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Funcionários")
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo funcionário")
    public Funcionario salvar(@RequestBody Funcionario funcionario) {
        return service.salvar(funcionario);
    }

    @GetMapping
    @Operation(summary = "Listar funcionários")
    public List<FuncionarioDTO> listarTodos() {
        return service.listarTodos().stream().map(FuncionarioDTO::new).toList();
    }

    @DeleteMapping("/{nome}")
    @Operation(summary = "Deletar funcionário")
    public void removerPorNome(@PathVariable String nome) {
        service.removerPorNome(nome);
    }

    @PutMapping("/aumentar-salario")
    @Operation(summary = "Aumentar salário dos funcionários")
    public void aumentarSalario(@RequestParam double percentual) {
        service.aumentarSalario(percentual);
    }

    @GetMapping("/agrupar-por-funcao")
    @Operation(summary = "Agrupar funcionários por função")
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return service.agruparPorFuncao();
    }

    @GetMapping("/mais-velho")
    @Operation(summary = "Listar funcionário mais velho")
    public FuncionarioMaisVelhoDTO obterFuncionarioMaisVelho() {
        return service.obterFuncionarioMaisVelho();
    }

    @GetMapping("/aniversariantes-out-dez")
    @Operation(summary = "Listar funcionários que fazem aniversário em outubro e dezembro")
    public List<Funcionario> listarAniversariantesOutubroDezembro() {
        return service.listarAniversariantesOutubroDezembro();
    }

    @GetMapping("/ordenados")
    @Operation(summary = "Ordenar funcionários ordem alfabética")
    public List<Funcionario> ordenarPorNome() {
        return service.ordenarPorNome();
    }

    @GetMapping("/total-salarios")
    @Operation(summary = "Listar a soma de todos os salários")
    public BigDecimal calcularTotalSalarios() {
        return service.calcularTotalSalarios();
    }

    @GetMapping("/quantidade-salarios-minimos")
    @Operation(summary = "Listar quantos salários minimos cada funcionário possue")
    public Map<String, BigDecimal> calcularSalariosMinimos() {
        return service.calcularSalariosMinimos();
    }
}
