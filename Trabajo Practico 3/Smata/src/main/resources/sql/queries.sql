---Consultas Coche---

--Buscar por ID
SELECT * FROM Coches 
WHERE idCoche = ?;

--Listar todos los coches
SELECT c.*, m.nombre AS nombre_motor 
FROM Coches c 
JOIN Motores m ON c.idMotor = m.idMotor;

--Buscar por marca
SELECT * FROM Coches 
WHERE marca LIKE ?;

--Actualizar precio
UPDATE Coches 
SET precio = ? 
WHERE idCoche = ?;

--Eliminar coche
DELETE FROM Coches 
WHERE idCoche = ?;

---Consultas Empleado---

SELECT idEmpleado, rol, nombre 
FROM Empleados 
WHERE usuario = ? AND password = ?;

--Listar todos los empleados
SELECT idEmpleado, nombre, apellido, rol, usuario 
FROM Empleados;

--Actualizar rol y contraseña
UPDATE Empleados 
SET rol = ?, password = ? 
WHERE idEmpleado = ?;

--Eliminar un empleado
DELETE FROM Empleados 
WHERE idEmpleado = ?;