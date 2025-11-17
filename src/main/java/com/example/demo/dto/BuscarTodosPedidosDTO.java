package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BuscarTodosPedidosDTO(List<ItemPedidoDTO> itens, String usuarioEmail, Double valor_total, com.example.demo.model.StatusPedido status, String forma_pagamento, String endereco, LocalDateTime dataCriacao) {
}
