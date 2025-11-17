package com.example.demo.controller;

import com.example.demo.dto.PedidosDTO;
import com.example.demo.model.PedidosEntity;
import com.example.demo.model.Usuario;
import com.example.demo.service.pedidos.PedidosService;
import com.example.demo.service.seguranca.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar")
    public ResponseEntity<List<PedidosEntity>> buscarPedidos(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String email = usuario.getEmail();
        List<PedidosEntity> pedidos = pedidosService.listarPedidosPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }
}
