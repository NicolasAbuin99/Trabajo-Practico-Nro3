package Trabajo.Practico.Nro3.repositorios.interfaces;

import java.sql.SQLException;
import java.util.List;

import Trabajo.Practico.Nro3.entidades.Coches;

public interface ICochesRepository {
    
    void crear(Coches coche) throws SQLException;

    int actualizar(Coches coche) throws SQLException;

    int eliminar(int idCoche) throws SQLException;

    List<Coches> buscarTodos() throws SQLException;

    Coches buscarPorId(int idCoche) throws SQLException;


}
