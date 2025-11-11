CREATE TABLE pedidos(
    id TEXT PRIMARY KEY NOT NULL,
    usuario_id TEXT FOREIGN KEY,
    valor_total DECIMAL(10, 2) NOT NULL,
    forma_pagamento TEXT NOT NULL,
    endereco TEXT NOT NULL,
    status ENUM('processando', 'preparando', 'enviado', 'entregue') DEFAULT 'processando',
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    FOREIGN KEY (usuario_id) REFERENCES acesso_usuario(id)
);