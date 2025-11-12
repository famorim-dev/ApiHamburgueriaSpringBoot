package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
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

    private String itens;

    private String usuario_id;

    private Double valor_total;

    private String forma_pagamento;

    private String endereco;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao =  LocalDateTime.now();
}