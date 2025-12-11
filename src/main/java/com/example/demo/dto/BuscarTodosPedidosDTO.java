package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.model.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record BuscarTodosPedidosDTO(List<ItemPedidoDTO> itens, String usuarioEmail, Double valor_total, StatusPedido status, String forma_pagamento, String endereco, LocalDateTime dataCriacao, Role role) {
}
