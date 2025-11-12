USE concesionaria;

-- Insertar datos en motores
INSERT INTO Motores (idMotor, nombre, cilindrada) VALUES 
(1, 'Nafta', '1.6L'),
(2, 'Diésel', '2.0L'),
(3, 'Eléctrico', 'N/A'),
(4, 'Híbrido', '1.8L');


-- Insertar datos en empleados
INSERT INTO Empleados (idEmpleado, nombre, apellido, rol, usuario, password) VALUES 
(101, 'Juan', 'Pérez', 'Administrador', 'jperez', 'admin123'),
(102, 'Ana', 'Gómez', 'Vendedor', 'agomez', 'vendedor456');


-- Insertar datos en coches
INSERT INTO Coches (idCoche, marca, modelo, precio, anio, color, carroceria, idMotor) VALUES 
(1, 'Ford', 'Focus', 15000.00, 2020, 'Rojo', 'Sedán', 1),    -- Motor Nafta (ID 1)
(2, 'Toyota', 'Corolla', 22000.50, 2022, 'Gris', 'Sedán', 4), -- Motor Híbrido (ID 4)
(3, 'Audi', 'E-Tron', 65000.00, 2023, 'Negro', 'SUV', 3),    -- Motor Eléctrico (ID 3)
(4, 'VW', 'Amarok', 35000.00, 2021, 'Blanco', 'Camioneta', 2); -- Motor Diésel (ID 2)


-- Insertar datos en registros
INSERT INTO Registros (idRegistro, descripcion, idCoche, idEmpleado) VALUES 
(1, 'Focus basico', 1, 101),       -- Coche 1, Registrado por Admin (101)
(2, 'Corolla basico', 2, 102),     -- Coche 2, Registrado por Vendedor (102)
(3, 'Auto electrico', 3, 101);