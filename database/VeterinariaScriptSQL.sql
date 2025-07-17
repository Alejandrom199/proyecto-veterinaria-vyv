CREATE DATABASE IF NOT EXISTS veterinaria;
USE veterinaria;

CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE Mascota (
    idMascota INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especie VARCHAR(50),
    raza VARCHAR(50),
    edad INT,
    sexo VARCHAR(10),
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE Veterinario (
    idVeterinario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE Cita (
    idCita INT AUTO_INCREMENT PRIMARY KEY,
    fechaHora DATETIME NOT NULL,
    motivo VARCHAR(255),
    estado VARCHAR(20),
    idMascota INT NOT NULL,
    idVeterinario INT NOT NULL,
    FOREIGN KEY (idMascota) REFERENCES Mascota(idMascota),
    FOREIGN KEY (idVeterinario) REFERENCES Veterinario(idVeterinario)
);

CREATE TABLE Servicio (
    idServicio INT AUTO_INCREMENT PRIMARY KEY,
    nombreServicio VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Medicamento(
    idMedicamento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Factura (
    idFactura INT AUTO_INCREMENT PRIMARY KEY,
    fechaEmision DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE DetalleFactura (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idFactura INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    idServicio INT,
    idProducto INT, -- hace referencia a Medicamento
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),
    FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio),
    FOREIGN KEY (idProducto) REFERENCES Medicamento(idMedicamento)
);

#init Usuario
INSERT INTO Usuario (nombreUsuario, contrasena, rol)
SELECT 'admin', 'admin123', 'administrador'
WHERE NOT EXISTS (SELECT 1 FROM Usuario WHERE nombreUsuario = 'admin');

# Usando comandos SQL
SELECT * FROM Usuario;
SELECT * FROM Cliente;
SELECT * FROM Mascota;
SELECT * FROM Cita;
SELECT * FROM Medicamento;
SELECT * FROM Factura;
SELECT * FROM DetalleFactura;
SELECT * FROM Servicio WHERE idServicio = 2;

#Eliminacion de tablas
DROP TABLE Medicamento;
DROP TABLE Factura;
DROP TABLE DetalleFactura;

SELECT 
    m.nombre AS medicamento,
    SUM(df.cantidad) AS total_vendido
FROM 
    DetalleFactura df
JOIN 
    Medicamento m ON df.idProducto = m.idMedicamento
WHERE 
    df.tipo = 'medicamento'
GROUP BY 
    m.idMedicamento, m.nombre
ORDER BY 
    total_vendido DESC;

