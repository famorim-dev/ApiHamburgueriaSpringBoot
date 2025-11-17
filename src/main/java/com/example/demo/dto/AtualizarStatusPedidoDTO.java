package com.example.demo.dto;

import com.example.demo.model.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusPedidoDTO(@NotNull StatusPedido status) {
}
