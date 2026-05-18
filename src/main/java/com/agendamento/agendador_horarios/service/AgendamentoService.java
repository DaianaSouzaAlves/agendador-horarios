package com.agendamento.agendador_horarios.service;

import com.agendamento.agendador_horarios.dto.AgendamentoRequestDTO;
import com.agendamento.agendador_horarios.dto.AgendamentoResponseDTO;
import com.agendamento.agendador_horarios.entity.Agendamento;
import com.agendamento.agendador_horarios.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public AgendamentoResponseDTO salvar(AgendamentoRequestDTO dto) {

        if (repository.existsByDataHoraAgendamento(dto.getDataHoraAgendamento())) {
            throw new RuntimeException("Horário já agendado");
        }

        Agendamento agendamento = new Agendamento();

        agendamento.setServico(dto.getServico());
        agendamento.setProfissional(dto.getProfissional());
        agendamento.setDataHoraAgendamento(dto.getDataHoraAgendamento());
        agendamento.setCliente(dto.getCliente());

        repository.save(agendamento);

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getServico(),
                agendamento.getProfissional(),
                agendamento.getDataHoraAgendamento(),
                agendamento.getCliente()
        );
    }

    public List<AgendamentoResponseDTO> listarTodos() {

        return repository.findAll()
                .stream()
                .map(a -> new AgendamentoResponseDTO(
                        a.getId(),
                        a.getServico(),
                        a.getProfissional(),
                        a.getDataHoraAgendamento(),
                        a.getCliente()
                ))
                .toList();
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getServico(),
                agendamento.getProfissional(),
                agendamento.getDataHoraAgendamento(),
                agendamento.getCliente()
        );
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        if (!agendamento.getDataHoraAgendamento().equals(dto.getDataHoraAgendamento())
                && repository.existsByDataHoraAgendamento(dto.getDataHoraAgendamento())) {

            throw new RuntimeException("Horário já agendado");
        }

        agendamento.setServico(dto.getServico());
        agendamento.setProfissional(dto.getProfissional());
        agendamento.setDataHoraAgendamento(dto.getDataHoraAgendamento());
        agendamento.setCliente(dto.getCliente());

        repository.save(agendamento);

        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getServico(),
                agendamento.getProfissional(),
                agendamento.getDataHoraAgendamento(),
                agendamento.getCliente()
        );
    }

    public void deletar(Long id) {

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        repository.delete(agendamento);
    }

    public List<AgendamentoResponseDTO> buscarPorCliente(String cliente) {

        return repository.findByClienteContainingIgnoreCase(cliente)
                .stream()
                .map(a -> new AgendamentoResponseDTO(
                        a.getId(),
                        a.getServico(),
                        a.getProfissional(),
                        a.getDataHoraAgendamento(),
                        a.getCliente()
                ))
                .toList();
    }
}
