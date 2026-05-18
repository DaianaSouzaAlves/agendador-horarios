package com.agendamento.agendador_horarios.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendamentoRequestDTO {

    @NotBlank (message = "Serviço obrigatório")
    private String servico;

    @NotBlank (message = "Profissional obrigatório")
    private String profissional;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull (message = "Data e hora obrigatória")
    private LocalDateTime dataHoraAgendamento;

    @NotBlank (message = "Cliente obrigatório")
    private String cliente;

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public LocalDateTime getDataHoraAgendamento() {
        return dataHoraAgendamento;
    }

    public void setDataHoraAgendamento(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
