package com.agendamento.agendador_horarios.controller;

import com.agendamento.agendador_horarios.dto.AgendamentoRequestDTO;
import com.agendamento.agendador_horarios.dto.AgendamentoResponseDTO;
import com.agendamento.agendador_horarios.entity.Agendamento;
import com.agendamento.agendador_horarios.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    //Cadastrar
    @Operation(summary = "Cadastrar agendamento")
    @PostMapping
    public AgendamentoResponseDTO cadastrar(
            @Valid @RequestBody AgendamentoRequestDTO dto) {
        return service.salvar(dto);
    }

    //Listar todos
    @Operation(summary = "Listar todos agendamentos")
    @GetMapping
    public List<AgendamentoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    //Buscar por id
    @Operation(summary = "Buscar agendamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    //Atualizar
    @Operation(summary = "Atualuzar agendamento")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AgendamentoRequestDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    //Deletar
    @Operation(summary = "Deletar agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

    //Buscar por cliente
    @Operation(summary = "Buscar agendamento por cliente")
    @GetMapping("/cliente")
    public ResponseEntity<List<AgendamentoResponseDTO>> buscarPorCliente(
            @RequestParam String nome) {

        return ResponseEntity.ok(service.buscarPorCliente(nome));
    }
}
