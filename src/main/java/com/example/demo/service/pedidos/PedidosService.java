package com.example.demo.service.pedidos;

import com.example.demo.dto.ItemPedidoDTO;
import com.example.demo.dto.PedidosDTO;
import com.example.demo.model.PedidosEntity;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.seguranca.UsuarioLogadoService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidosService {
    private final PedidosRepository pedidosRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioLogadoService usuarioLogadoService;

    private static final Map<String, Double> CARDAPIO = Map.of(
            "Hamburguer duplo", 22.90,
            "Hamburguer salad", 28.90,
            "Hamburguer smash", 35.90,
            "Hamburguer salad queijo", 29.90,
            "Hamburguer queijo + batata", 39.90,
            "Coca lata", 5.90,
            "Guaraná lata", 5.90
    );

    public PedidosService(PedidosRepository pedidosRepository, UsuarioRepository usuarioRepository, UsuarioLogadoService usuarioLogadoService){
        this.pedidosRepository = pedidosRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioLogadoService = usuarioLogadoService;
    }

    public PedidosDTO registrarPedidos(PedidosDTO data){
        var auth = usuarioLogadoService.getUsuarioLogado();

        double valorTotal = 0;
        for (ItemPedidoDTO item : data.itens()){
            Double precoUnitario = CARDAPIO.get(item.produto());
            if (precoUnitario == null){
                throw new RuntimeException("Produto não encontrado: " + item.produto());
            }
            valorTotal += precoUnitario * item.quantidade();
        }

        PedidosEntity pedidosEntity = new PedidosEntity();
        pedidosEntity.setUsuario(auth);
        pedidosEntity.setItens(data.itens());
        pedidosEntity.setValor_total(valorTotal);
        pedidosEntity.setForma_pagamento(data.forma_pagamento());
        pedidosEntity.setEndereco(data.endereco());

        PedidosEntity pedidosEntitySalvo = pedidosRepository.save(pedidosEntity);
        return new PedidosDTO(pedidosEntitySalvo.getItens(), pedidosEntitySalvo.getForma_pagamento(), pedidosEntitySalvo.getValor_total(), pedidosEntitySalvo.getEndereco(), pedidosEntitySalvo.getUsuario().getId().toString());
    }

    public List<PedidosEntity> listarPedidosPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pedidosRepository.findByUsuarioId(usuario.getId());
    }
}
