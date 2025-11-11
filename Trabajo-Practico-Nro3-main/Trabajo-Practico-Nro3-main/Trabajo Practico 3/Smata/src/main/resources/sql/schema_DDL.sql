DROP DATABASE IF EXISTS concesionaria;
CREATE DATABASE concesionaria;
USE concesionaria;

CREATE TABLE Motores(
    idMotores INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    cilindrara VARCHAR(10)
);

CREATE TABLE Empleado(
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    usuario VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE Coches(
    idCoche INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    precio DECIMAL (10,2),
    anio INT,
    color VARCHAR(45) NOT NULL,
    carroceria VARCHAR(45) NOT NULL,
    Motores_idMotores INT NOT NULL, 
    FOREIGN KEY (Motores_idMotores) REFERENCES Motores(idMotores)
);

CREATE TABLE Registros (
    idRegistros INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    Coches_idCoches INT NOT NULL,
    Empleado_idEmpleado INT NOT NULL,
    FOREIGN KEY (Coches_idCoches) REFERENCES Coches(idCoches),
    FOREIGN KEY (Empleado_idEmpleado) REFERENCES Empleado(idEmpleado)
);