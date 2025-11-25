package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PedidoTeste extends BaseTest {

    @Test
    void criarPedido() throws Exception {

        String json = """
                {
                    "itens":[{"produto":"Hamburguer duplo","quantidade":2}],
                    "forma_pagamento":"pix",
                    "endereco":"Rua X"
                }
                """;

        mockMvc.perform(
                post("/pedido/registro")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());
    }


    @Test
    void buscarUmPedido() throws Exception {

        mockMvc.perform(
                get("/pedido/buscar")
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk());
    }
}
