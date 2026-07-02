-- Inicialización de la Base de Datos de Pedidos
CREATE SCHEMA IF NOT EXISTS pedidos;

-- Tabla de Pedidos
CREATE TABLE pedidos.pedidos (
    id SERIAL PRIMARY KEY,
    numero_orden VARCHAR(50) NOT NULL UNIQUE,
    cliente VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Items del Pedido
CREATE TABLE pedidos.items_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES pedidos.pedidos(id) ON DELETE CASCADE,
    producto_id INTEGER NOT NULL,
    nombre_producto VARCHAR(255) NOT NULL,
    cantidad INTEGER NOT NULL,
    precio_unitario NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para mejora de performance
CREATE INDEX idx_pedidos_numero_orden ON pedidos.pedidos(numero_orden);
CREATE INDEX idx_pedidos_estado ON pedidos.pedidos(estado);
CREATE INDEX idx_items_pedido_id ON pedidos.items_pedido(pedido_id);

-- Insertar datos de ejemplo
INSERT INTO pedidos.pedidos (numero_orden, cliente, email, total, estado) VALUES
('PED-001', 'Juan Pérez', 'juan.perez@example.com', 1599.99, 'CONFIRMADO'),
('PED-002', 'María García', 'maria.garcia@example.com', 349.99, 'PENDIENTE'),
('PED-003', 'Carlos López', 'carlos.lopez@example.com', 2219.98, 'ENVIADO');

-- Insertar items de ejemplo
INSERT INTO pedidos.items_pedido (pedido_id, producto_id, nombre_producto, cantidad, precio_unitario) VALUES
(1, 1, 'Laptop Dell XPS 13', 1, 1299.99),
(1, 2, 'Auriculares Sony WH-1000XM5', 1, 349.99),
(2, 4, 'Auriculares Sony WH-1000XM5', 1, 349.99),
(3, 2, 'iPhone 15 Pro', 2, 999.99),
(3, 3, 'iPad Air', 1, 219.99);

COMMIT;
