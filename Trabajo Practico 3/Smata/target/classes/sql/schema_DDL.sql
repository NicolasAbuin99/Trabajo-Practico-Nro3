DROP DATABASE IF EXISTS concesionaria;
CREATE DATABASE concesionaria;
USE concesionaria;

CREATE TABLE Motores(
    idMotor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    cilindrada VARCHAR(45)
);

CREATE TABLE Empleados(
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    rol VARCHAR(45) NOT NULL,
    usuario VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE Coches(
    idCoche INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    precio DECIMAL (10,2),
    anio INT NOT NULL,
    color VARCHAR(45) NOT NULL,
    carroceria VARCHAR(45) NOT NULL,
    idMotor INT NOT NULL, 
    FOREIGN KEY (idMotor) REFERENCES Motores(idMotor)
);

CREATE TABLE Registros (
    idRegistro INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    idCoche INT NOT NULL,
    idEmpleado INT NOT NULL,
    FOREIGN KEY (idCoche) REFERENCES Coches(idCoche),
    FOREIGN KEY (idEmpleado) REFERENCES Empleados(idEmpleado)
);