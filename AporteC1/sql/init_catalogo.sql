-- Inicialización de la Base de Datos de Catálogo
CREATE SCHEMA IF NOT EXISTS catalogo;

-- Tabla de Productos
CREATE TABLE catalogo.productos (
    id SERIAL PRIMARY KEY,
    sku VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio NUMERIC(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índices para mejora de performance
CREATE INDEX idx_productos_sku ON catalogo.productos(sku);
CREATE INDEX idx_productos_activo ON catalogo.productos(activo);

-- Insertar datos de ejemplo
INSERT INTO catalogo.productos (sku, nombre, descripcion, precio, stock, activo) VALUES
('LAPTOP-001', 'Laptop Dell XPS 13', 'Laptop ultraportátil con procesador Intel i7', 1299.99, 15, TRUE),
('PHONE-001', 'iPhone 15 Pro', 'Smartphone Apple con cámara profesional', 999.99, 25, TRUE),
('TABLET-001', 'iPad Air', 'Tablet de alta gama con pantalla Retina', 599.99, 20, TRUE),
('ACCES-001', 'Auriculares Sony WH-1000XM5', 'Auriculares con cancelación de ruido activa', 349.99, 30, TRUE),
('ACCES-002', 'Cable USB-C', 'Cable universal para carga y datos', 19.99, 100, TRUE);

COMMIT;
