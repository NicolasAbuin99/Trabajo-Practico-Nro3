package Trabajo.Practico.Nro3.repositorios;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Trabajo.Practico.Nro3.entidades.Coches;
import Trabajo.Practico.Nro3.repositorios.interfaces.ICochesRepository;

public class CochesRepository implements ICochesRepository {

    private final DataSource dataSource;

    private static final String SQL_CREATE =
    "INSERT INTO coches(marca,modelo,precio,anio,color,carroceria) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_ALL =
    "SELECT * FROM coches";
    private static final String SQL_FIND_BY_ID =
    "SELECT * FROM coches WHERE idCoche = ?";
    private static final String SQL_UPDATE =
    "UPDATE coches SET marca=?,modelo=?,precio=?,anio=?,color=?,carroceria=? WHERE idCoche=?";
    private static final String SQL_DELETE =
    "DELETE FROM coches WHERE idCoche=?";

    public CochesRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public void crear(Coches coche) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,coche.getMarca());
            ps.setString(2,coche.getModelo());
            ps.setDouble(3,coche.getPrecio());
            ps.setInt(4,coche.getAnio());
            ps.setString(5,coche.getColor());
            ps.setString(6,coche.getCarroceria());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    coche.setIdCoche(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public int actualizar(Coches coche) throws SQLException {
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)){
            ps.setString(1,coche.getMarca());
            ps.setString(2,coche.getModelo());
            ps.setDouble(3,coche.getPrecio());
            ps.setInt(4,coche.getAnio());
            ps.setString(5,coche.getColor());
            ps.setString(6,coche.getCarroceria());
            ps.setInt(7,coche.getIdCoche);
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas;
        }
    }


    @Override
    public int eliminar(int idCoche) throws SQLException {
        try (Connection conn  = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_DELETE)){
            ps.setInt(1,idCoche);
            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas;
        }
    }

    @Override
    public List<Coches> buscarTodos() throws SQLException {
        List<Coches> coche = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                coche.add(mapRow(rs));
            }
        }
        return coche;
    }

    @Override
    public Coches buscarPorId(int idCoche) throws SQLException {
         try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1,idCoche);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    private Coches mapRow(ResultSet rs) throws  SQLException{
        Coches c = new Coches();
        c.setIdCoche(rs.getInt("idCoche"));
        c.setMarca(rs.getString("marca"));
        c.setModelo(rs.getString("modelo"));
        c.setPrecio(rs.getDouble("precio"));
        c.setAnio(rs.getInt("anio"));
        c.setColor(rs.getString("color"));
        c.setCarroceria(rs.getString("carroceria"));
        return c;
    }

}
