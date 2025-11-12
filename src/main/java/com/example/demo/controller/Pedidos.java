package com.example.demo.controller;

import com.example.demo.dto.PedidosDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedido")
public class Pedidos {

    @PostMapping("/registro")
    public ResponseEntity registrarPedido(@RequestHeader("Authorization") String authHeader, @RequestBody @Valid PedidosDTO data){

    }
}
