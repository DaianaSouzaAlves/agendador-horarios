package com.agendamento.agendador_horarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    // Regras de negócio
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("dataHora", LocalDateTime.now());
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("erro", "Regra de negócio");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    // Validações do DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> campos = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            campos.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> erro = new HashMap<>();

        erro.put("dataHora", LocalDateTime.now());
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("erro", "Erro de validação");
        erro.put("mensagens", campos);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    // Qualquer erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {

        Map<String, Object> erro = new HashMap<>();

        erro.put("dataHora", LocalDateTime.now());
        erro.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        erro.put("erro", "Erro interno");
        erro.put("mensagem", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }
}
