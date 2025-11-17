package com.example.demo.service.pedidos;

import com.example.demo.dto.ItemPedidoDTO;
import com.example.demo.dto.PedidosDTO;
import com.example.demo.model.PedidosEntity;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidosService {
    private final PedidosRepository pedidosRepository;
    private final UsuarioRepository usuarioRepository;

    private static final Map<String, Double> CARDAPIO = Map.of(
            "BurguerComum", 15.0,
            "burguerBox", 25.0,
            "BatataMedia", 10.0,
            "Refrigerante", 8.0
    );

    public PedidosService(PedidosRepository pedidosRepository, UsuarioRepository usuarioRepository){
        this.pedidosRepository = pedidosRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PedidosDTO registrarPedidos(PedidosDTO data){
        Authentication token = SecurityContextHolder.getContext().getAuthentication();
        String email = token.getName();

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("usuário invalido"));

        double valorTotal = 0;
        for (ItemPedidoDTO item : data.itens()){
            Double precoUnitario = CARDAPIO.get(item.produto());
            if (precoUnitario == null){
                throw new RuntimeException("Produto não encontrado: " + item.produto());
            }
            valorTotal += precoUnitario * item.quantidade();
        }

        PedidosEntity pedidosEntity = new PedidosEntity();
        pedidosEntity.setUsuario(usuario);
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
