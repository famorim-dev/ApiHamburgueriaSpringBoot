package com.example.demo.repository;

import com.example.demo.model.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidosRepository extends JpaRepository<PedidosEntity, UUID> {
    List<PedidosEntity> findByUsuarioId(UUID id);
}
