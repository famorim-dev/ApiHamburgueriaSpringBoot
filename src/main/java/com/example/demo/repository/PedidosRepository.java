package com.example.demo.repository;

import com.example.demo.model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidosRepository extends JpaRepository<Pedidos, UUID> {
}
