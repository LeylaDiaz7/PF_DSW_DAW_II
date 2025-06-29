-- Crear base de datos

drop database BDHoliday;
CREATE DATABASE IF NOT EXISTS BDHoliday;

USE BDHoliday;
show tables;

-- Tabla de Tipos de Habitación
CREATE TABLE tipos_habitacion (
    id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    capacidad INT NOT NULL,
    precio_base DECIMAL(10, 2) NOT NULL
);

-- Tabla de Habitaciones (modificada para referenciar tipos)
CREATE TABLE habitaciones (
    id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
    numero_habitacion VARCHAR(10) NOT NULL UNIQUE,
    id_tipo INT NOT NULL,
    estado ENUM('Disponible', 'Ocupada', 'Mantenimiento', 'Reservada') NOT NULL DEFAULT 'Disponible',
    FOREIGN KEY (id_tipo) REFERENCES tipos_habitacion(id_tipo)
);

-- Tabla de Usuarios (clientes)
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Reservas
CREATE TABLE reservas (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_habitacion INT,
    fecha_llegada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    cantidad_personas INT NOT NULL,
    estado_reserva ENUM('Confirmada', 'Cancelada', 'Completada', 'Pendiente') NOT NULL DEFAULT 'Confirmada',
    total DECIMAL(10, 2) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion),
    CHECK (fecha_salida > fecha_llegada)
);

-- Tabla de Pagos (opcional)
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_reserva INT,
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    monto DECIMAL(10, 2) NOT NULL,
    metodo_pago ENUM('Efectivo', 'Tarjeta', 'Transferencia') NOT NULL,
    estado_pago ENUM('Pendiente', 'Completado', 'Rechazado') NOT NULL DEFAULT 'Pendiente',
    FOREIGN KEY (id_reserva) REFERENCES reservas(id_reserva)
);
select*from habitaciones;

-- Insertar datos básicos de tipos de habitación
INSERT INTO tipos_habitacion (nombre, descripcion, capacidad, precio_base) VALUES
('Individual', 'Habitación con cama individual', 1, 80.00),
('Doble', 'Habitación con cama matrimonial o dos camas individuales', 2, 120.00),
('Suite', 'Amplia habitación con sala de estar separada', 4, 200.00),
('Familiar', 'Habitación grande para familias', 5, 180.00);

-- Insertar algunas habitaciones de ejemplo
INSERT INTO habitaciones (numero_habitacion, id_tipo, estado) VALUES
('101', 1, 'Disponible'),
('102', 1, 'Disponible'),
('201', 2, 'Disponible'),
('202', 2, 'Disponible'),
('301', 3, 'Disponible'),
('401', 4, 'Disponible');

SELECT * FROM habitaciones;

-- -----------------------------
INSERT INTO usuarios (nombre, apellido, email, contraseña) VALUES
('Ana', 'Pérez', 'ana.perez@gmail.com', 'pass123'),
('Carlos', 'Ramírez', 'carlos.ramirez@gmail.com', 'secure456'),
('Luisa', 'Gonzales', 'luisa.gonzales@gmail.com', 'clave789');
INSERT INTO usuarios (nombre, apellido, email, contraseña) VALUES
('Leyla', 'Diaz', 'leyla.diaz@gmail.com', 'papas123');
INSERT INTO usuarios (nombre, apellido, email, contraseña) VALUES
('Bruno', 'Santos', 'bruno.santos@gmail.com', 'abc123');

select * from usuarios;

INSERT INTO reservas (id_usuario, id_habitacion, fecha_llegada, fecha_salida, cantidad_personas, total) VALUES
(1, 1, '2025-07-01', '2025-07-03', 1, 160.00),
(2, 2, '2025-07-05', '2025-07-08', 2, 360.00),
(3, 3, '2025-07-10', '2025-07-14', 3, 800.00);

SELECT id_reserva FROM reservas;
SELECT * FROM reservas;

INSERT INTO pagos (id_reserva, monto, metodo_pago, estado_pago) VALUES
(1, 160.00, 'Tarjeta', 'Completado'),
(2, 360.00, 'Efectivo', 'Pendiente'),
(3, 800.00, 'Transferencia', 'Completado');

select*from pagos;
-- Mostrar mensaje de éxito
SELECT 'Base de datos BDHoliday creada exitosamente con datos de ejemplo!' AS mensaje;