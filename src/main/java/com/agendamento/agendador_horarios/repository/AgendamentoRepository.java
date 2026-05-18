package com.agendamento.agendador_horarios.repository;

import com.agendamento.agendador_horarios.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository
        extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByClienteContainingIgnoreCase(String cliente);

    boolean existsByDataHoraAgendamento(LocalDateTime dataHoraAgendamento);
}
