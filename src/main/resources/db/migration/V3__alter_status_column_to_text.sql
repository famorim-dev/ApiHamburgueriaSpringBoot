ALTER TABLE pedidos
    ALTER COLUMN status DROP DEFAULT;

ALTER TABLE pedidos
    ALTER COLUMN status TYPE TEXT
    USING status::TEXT;

ALTER TABLE pedidos
    ALTER COLUMN status SET DEFAULT 'processando';

DROP TYPE IF EXISTS status_pedido CASCADE;
