package com.agendamento.agendador_horarios.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(
        info = @Info(
                title = "API de Agendamentos",
                version = "1.0",
                description = "API REST para gerenciamento de horários"
        )
)

public class ApiConfig {
}