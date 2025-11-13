
-- ===============================
-- TABLAS PRINCIPALES
-- ===============================

-- TABLA USUARIO
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(100) NOT NULL,
    rol ENUM('Administrador','Vendedor') NOT NULL
);

-- TABLA CLIENTE
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150),
    telefono VARCHAR(20)
);

-- TABLA PRODUCTO
CREATE TABLE Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

-- TABLA VENTA
CREATE TABLE Venta (
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT NOT NULL,
    idCliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(10,2),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

-- TABLA DETALLE DE VENTA
CREATE TABLE Detalle_Venta (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (idVenta) REFERENCES Venta(idVenta),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

-- TABLA REPORTE (opcional, para resumenes o informes)
CREATE TABLE Reporte (
    idReporte INT AUTO_INCREMENT PRIMARY KEY,
    fechaGeneracion DATE NOT NULL
);

-- ===============================
-- INSERTS INICIALES
-- ===============================

-- USUARIOS
INSERT INTO Usuario (nombre, apellido, usuario, clave, rol)
VALUES 
('Ana', 'Gómez', 'agomez', '1234', 'Administrador'),
('Carlos', 'López', 'clopez', '5678', 'Vendedor');

-- CLIENTES
INSERT INTO Cliente (nombre, apellido, email, telefono)
VALUES 
('Pedro', 'Martínez', 'pedro@mail.com', '11335577'),
('Lucía', 'Fernández', 'lucia@mail.com', '11774455');

-- PRODUCTOS
INSERT INTO Producto (nombre, categoria, precio, stock)
VALUES 
('Monitor', 'Electrónica', 80000.00, 10),
('Teclado', 'Periféricos', 15000.00, 25);

-- OPCIONAL: crear una venta de prueba
INSERT INTO Venta (idUsuario, idCliente, fecha, total)
VALUES (2, 1, CURDATE(), 95000.00);

INSERT INTO Detalle_Venta (idVenta, idProducto, cantidad, precioUnitario, subtotal)
VALUES 
(1, 1, 1, 80000.00, 80000.00),
(1, 2, 1, 15000.00, 15000.00);
