package Trabajo.Practico.Nro3.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Trabajo.Practico.Nro3.entidades.Registros;
import Trabajo.Practico.Nro3.repositorios.interfaces.IRegistrosRepository;

public class RegistrosRepository implements IRegistrosRepository{

    private final DataSource dataSource;

private static final String SQL_CREATE =
    "INSERT INTO registros(descripcion) VALUES (?)";
    private static final String SQL_FIND_ALL =
    "SELECT * FROM registros";
    private static final String SQL_FIND_BY_ID =
    "SELECT * FROM registros WHERE idRegistro = ?";

    public RegistrosRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void crear(Registros registro) throws SQLException {
        try (var conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,registro.getDescripcion());
            ps.executeUpdate();

            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    registro.setIdRegistro(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Registros buscarPorId(Registros registro) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1,registro.getIdRegistro());
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Registros> buscarTodos() throws SQLException {
        List<Registros> registros = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                registros.add(mapRow(rs));
            }
        }
        return registros;
    }

    private Registros mapRow(ResultSet rs) throws  SQLException{
        Registros r = new Registros();
        r.setIdRegistro(rs.getInt("idRegistro"));
        r.setDescripcion(rs.getString("descripcion"));
        return r;
    }

}
