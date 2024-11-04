CREATE DATABASE db_assignment;

\c db_assignment;

-- Crear la tabla 'clientes'
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    cliente_id VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE cuentas (
    id SERIAL PRIMARY KEY,
    estado BOOLEAN NOT NULL,
    saldo_inicial DECIMAL(10, 2) NOT NULL,
    saldo_disponible DECIMAL(10, 2) NOT NULL,
    numero_cuenta VARCHAR(255) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50), -- Ajusta el tipo según necesidad
    cliente_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE CASCADE
);


CREATE TABLE movimientos (
    id SERIAL PRIMARY KEY,
    valor DECIMAL(10, 2) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(50), -- Ajusta el tipo según necesidad
    cuenta_id INT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id) ON DELETE CASCADE
);