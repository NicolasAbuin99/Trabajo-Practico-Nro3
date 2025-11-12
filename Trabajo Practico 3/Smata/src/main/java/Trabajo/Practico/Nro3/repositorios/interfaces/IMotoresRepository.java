package Trabajo.Practico.Nro3.repositorios.interfaces;

import java.sql.SQLException;
import java.util.List;

import Trabajo.Practico.Nro3.entidades.Motores;

public interface IMotoresRepository {

    void crear(Motores motor) throws SQLException;

    Motores buscarPorId(int idMotor) throws SQLException;

    List<Motores> buscarTodos() throws SQLException;
}
