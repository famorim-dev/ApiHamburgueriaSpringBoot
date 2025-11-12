package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record PedidosDTO(@NotBlank String itens, @NotBlank String usuario_id, @NotBlank Double valor_total, @NotBlank String forma_pagamento, @NotBlank String endereco ) {
}
