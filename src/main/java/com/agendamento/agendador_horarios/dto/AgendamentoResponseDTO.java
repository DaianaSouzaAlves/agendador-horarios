package com.agendamento.agendador_horarios.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AgendamentoResponseDTO {

    private Long id;
    private String servico;
    private String profissional;
    private LocalDateTime dataHoraAgendamento;
    private  String cliente;

    public  AgendamentoResponseDTO () {}

    public AgendamentoResponseDTO(Long id, String servico, String profissional, LocalDateTime dataHoraAgendamento, String cliente) {
        this.id = id;
        this.servico = servico;
        this.profissional = profissional;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public String getServico() {
        return servico;
    }

    public String getProfissional() {
        return profissional;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public String getCliente() {
        return cliente;
    }
}
