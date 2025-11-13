package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PedidosDTO(List<ItemPedidoDTO> itens, @NotBlank String usuario_id, Double valor_total, @NotBlank String forma_pagamento, @NotBlank String endereco ) {
}
