package com.example.demo.service.seguranca;

import com.example.demo.dto.ItemPedidoDTO;
import com.example.demo.dto.PedidosDTO;
import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PedidosRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

        Pedidos pedidos = new Pedidos();
        pedidos.setUsuario(usuario);
        pedidos.setItens(data.itens());
        pedidos.setValor_total(data.valor_total());
        pedidos.setForma_pagamento(data.forma_pagamento());
        pedidos.setEndereco(data.endereco());

        Pedidos pedidosSalvo = pedidosRepository.save(pedidos);
        return new PedidosDTO(pedidosSalvo.getItens(), pedidosSalvo.getForma_pagamento(), pedidosSalvo.getValor_total(), pedidosSalvo.getEndereco(), pedidosSalvo.getUsuario().getId().toString());
    }
}
