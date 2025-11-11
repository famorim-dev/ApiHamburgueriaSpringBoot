package com.example.demo.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcecoesGlobais {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> ExcecoesGlobais(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor. Tente novamente mais tarde.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validaCampos(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos inválidos!");
    }
}
