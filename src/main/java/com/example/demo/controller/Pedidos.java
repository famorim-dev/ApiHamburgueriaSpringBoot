package com.example.demo.controller;

import com.example.demo.dto.AtualizarStatusPedidoDTO;
import com.example.demo.dto.BuscarTodosPedidosDTO;
import com.example.demo.dto.CancelarPedidoDTO;
import com.example.demo.dto.PedidosDTO;
import com.example.demo.mapper.pedidos.PedidosMapper;
import com.example.demo.model.PedidosEntity;
import com.example.demo.model.StatusPedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.service.pedidos.PedidosService;
import com.example.demo.service.seguranca.TokenService;
import com.example.demo.service.seguranca.UsuarioLogadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("pedido")
public class Pedidos {

    private final PedidosService pedidosService;
    private final PedidosRepository pedidosRepository;
    private final UsuarioLogadoService usuarioLogadoService;

    public Pedidos(PedidosRepository  pedidosRepository,PedidosService pedidosService, TokenService tokenService, UsuarioLogadoService usuarioLogadoService) {
        this.pedidosService = pedidosService;
        this.pedidosRepository = pedidosRepository;
        this.usuarioLogadoService = usuarioLogadoService;
    }

    @PostMapping("/registro")
    public ResponseEntity registrarPedido(@RequestBody @Valid PedidosDTO data){

        PedidosDTO novoPeido = pedidosService.registrarPedidos(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido registrado com sucesso");
    }

    @GetMapping("/buscar")
    public ResponseEntity buscarPedidos(){
        var auth = usuarioLogadoService.getUsuarioLogado();
        var pedidos = pedidosService.listarPedidosPorUsuario();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping("buscar-todos")
    public ResponseEntity<List<BuscarTodosPedidosDTO>> buscarTodosPedidos() {
        List<BuscarTodosPedidosDTO> pedidos = pedidosRepository.findAll().stream().map(PedidosMapper:: mapearTodosPedidos).toList();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @PatchMapping("/{id}/cancelado")
    public ResponseEntity cancelarPedido(@PathVariable UUID id, @RequestBody @Valid CancelarPedidoDTO dto){
        Optional<PedidosEntity> pedidoOpt = pedidosRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PedidosEntity pedido = pedidoOpt.get();
        pedido.setStatus(StatusPedido.cancelado);
        pedidosRepository.save(pedido);

        return ResponseEntity.ok(new AtualizarStatusPedidoDTO(pedido.getStatus()));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity editarStatus(@PathVariable UUID id, @RequestBody @Valid AtualizarStatusPedidoDTO dto){
        Optional<PedidosEntity> pedidoOpt = pedidosRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PedidosEntity pedido = pedidoOpt.get();
        pedido.setStatus(dto.status());
        pedidosRepository.save(pedido);

        return ResponseEntity.ok(new AtualizarStatusPedidoDTO(pedido.getStatus()));
    }
}
