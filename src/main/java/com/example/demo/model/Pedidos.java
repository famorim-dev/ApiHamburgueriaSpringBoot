package com.example.demo.model;

import com.example.demo.dto.ItemPedidoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "pedidos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedidos{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<ItemPedidoDTO> itens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double valor_total;

    private String forma_pagamento;

    private String endereco;

    @Column(name = "data_pedido", nullable = false, updatable = false)
    private LocalDateTime dataCriacao =  LocalDateTime.now();
}