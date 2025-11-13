package com.example.demo.controller;

import com.example.demo.dto.PedidosDTO;
import com.example.demo.service.seguranca.PedidosService;
import com.example.demo.service.seguranca.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedido")
public class Pedidos {

    private final PedidosService pedidosService;

    public Pedidos(PedidosService pedidosService, TokenService tokenService) {
        this.pedidosService = pedidosService;
    }

    @PostMapping("/registro")
    public ResponseEntity registrarPedido(@RequestBody @Valid PedidosDTO data){

        PedidosDTO novoPeido = pedidosService.registrarPedidos(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido registrado com sucesso");
    }
}
