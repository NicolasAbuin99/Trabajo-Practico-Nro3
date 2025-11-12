package Trabajo.Practico.Nro3.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Trabajo.Practico.Nro3.entidades.Empleados;
import Trabajo.Practico.Nro3.repositorios.interfaces.IEmpleadosRepository;

public class EmpleadosRepository implements IEmpleadosRepository {

    private final DataSource dataSource;

    private static final String SQL_CREATE =
    "INSERT INTO empleados(nombre,apellido,rol,usuario,password) VALUES (?,?,?,?,?)";
    private static final String SQL_FIND_ALL =
    "SELECT * FROM empleados";
    private static final String SQL_LOGIN =
    "SELECT * FROM empleados WHERE usuario = ? AND password = ?";
    private static final String SQL_UPDATE =
    "UPDATE empleado SET nombre=?,apellido=?,rol=?,usuario=?,password=? WHERE idEmpleado=?";
    private static final String SQL_DELETE =
    "DELETE FROM empleado WHERE idEmpleado=?";

    public EmpleadosRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public void crear(Empleados empleado) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,empleado.getNombre());
            ps.setString(2,empleado.getApellido());
            ps.setString(3,empleado.getRol());
            ps.setString(4,empleado.getUsuario());
            ps.setString(5,empleado.getPassword());
            
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    empleado.setIdEmpleado(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public int actualizar(Empleados empleado) throws SQLException {

        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)){
            ps.setString(1,empleado.getNombre());
            ps.setString(2,empleado.getApellido());
            ps.setString(3,empleado.getRol());
            ps.setString(4,empleado.getUsuario());
            ps.setString(5,empleado.getPassword());
            ps.setInt(7,empleado.getIdEmpleado());
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas;
        }
    }

    @Override
    public int eliminar(int idEmpleado) throws SQLException {
        try (Connection conn  = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_DELETE)){
            ps.setInt(1,idEmpleado);
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas;
        }
    }

    @Override
    public List<Empleados> buscarTodos() throws SQLException {
        List<Empleados> empleado = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                empleado.add(mapRow(rs));
            }
        }
        return empleado;
    }

    @Override
    public Empleados login(String usuario, String password) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_LOGIN)){
            
            ps.setString(1, usuario);
            ps.setString(2, password);
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRow(rs);
                }
            }
        }
        return null;
    }


    private Empleados mapRow(ResultSet rs) throws SQLException{
        Empleados e = new Empleados();
        e.setIdEmpleado(rs.getInt("idEmpleado"));
        e.setNombre(rs.getString("nombre"));
        e.setApellido(rs.getString("apellido"));
        e.setRol(rs.getString("rol"));
        e.setUsuario(rs.getString("usuario"));
        e.setPassword(rs.getString("password"));
        return e;
    }

}
