package Trabajo.Practico.Nro3.repositorios.interfaces;

import java.sql.SQLException;
import java.util.List;

import Trabajo.Practico.Nro3.entidades.Empleados;

public interface IEmpleadosRepository {

    void crear(Empleados empleado) throws SQLException;

    int actualizar(Empleados empleado) throws SQLException;

    int eliminar(int idEmpleado) throws SQLException;

    List<Empleados> buscarTodos() throws SQLException;

    Empleados login(String usuario, String password) throws SQLException;
}
