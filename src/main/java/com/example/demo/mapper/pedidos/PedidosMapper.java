package com.example.demo.mapper.pedidos;

import com.example.demo.dto.BuscarTodosPedidosDTO;
import com.example.demo.model.PedidosEntity;


public class PedidosMapper {
    public static BuscarTodosPedidosDTO mapearTodosPedidos(PedidosEntity pedidosEntity) {
       return new BuscarTodosPedidosDTO(
               pedidosEntity.getItens(),
               pedidosEntity.getUsuario().getEmail(),
               pedidosEntity.getValor_total(),
               pedidosEntity.getStatus(),
               pedidosEntity.getForma_pagamento(),
               pedidosEntity.getEndereco(),
               pedidosEntity.getDataCriacao(),
               pedidosEntity.getUsuario().getRole()
       );
    }
}
