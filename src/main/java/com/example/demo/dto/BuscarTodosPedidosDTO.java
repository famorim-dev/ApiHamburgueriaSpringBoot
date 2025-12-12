package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.model.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BuscarTodosPedidosDTO(UUID id, String nome, List<ItemPedidoDTO> itens, String usuarioEmail, Double valor_total, StatusPedido status, String forma_pagamento, String endereco, LocalDateTime dataCriacao, Role role) {
}
