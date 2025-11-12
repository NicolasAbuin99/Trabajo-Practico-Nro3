package Trabajo.Practico.Nro3.repositorios.interfaces;

import java.sql.SQLException;
import java.util.List;

import Trabajo.Practico.Nro3.entidades.Registros;



public interface IRegistrosRepository {

    void crear(Registros registro) throws SQLException;

    Registros buscarPorId(Registros registro) throws SQLException;

    List<Registros> buscarTodos() throws SQLException;
}
