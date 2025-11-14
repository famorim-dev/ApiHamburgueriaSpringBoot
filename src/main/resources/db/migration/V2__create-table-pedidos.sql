CREATE TYPE status_pedido AS ENUM (
    'processando',
    'preparando',
    'enviado',
    'entregue'
);

CREATE TABLE pedidos (
    id UUID PRIMARY KEY NOT NULL,
    itens TEXT NOT NULL,
    usuario_id UUID,
    valor_total DECIMAL(10, 2) NOT NULL,
    forma_pagamento TEXT NOT NULL,
    endereco TEXT NOT NULL,
    status status_pedido DEFAULT 'processando',
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (usuario_id) REFERENCES acesso_usuario(id)
);
