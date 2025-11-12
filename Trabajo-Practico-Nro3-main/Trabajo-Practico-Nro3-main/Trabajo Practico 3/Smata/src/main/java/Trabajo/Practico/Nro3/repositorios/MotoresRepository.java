package Trabajo.Practico.Nro3.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Trabajo.Practico.Nro3.entidades.Motores;
import Trabajo.Practico.Nro3.repositorios.interfaces.IMotoresRepository;

public class MotoresRepository implements IMotoresRepository{

    private final DataSource dataSource;

    private static final String SQL_CREATE =
    "INSERT INTO motores(nombre,cilindrada) VALUES (?,?)";
    private static final String SQL_FIND_ALL =
    "SELECT * FROM motores";
    private static final String SQL_FIND_BY_ID =
    "SELECT * FROM motores WHERE idMotor = ?";

    public MotoresRepository(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Override
    public void crear(Motores motor) throws SQLException {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,motor.getNombre());
            ps.setString(2,motor.getCilindrada());
            ps.executeUpdate();
            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    motor.setIdMotor(keys.getInt(1));
                }
            }
        }
    }

    @Override
    public Motores buscarPorId(int idMotor) throws SQLException {

        try (Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)){
            ps.setInt(1,idMotor);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Motores> buscarTodos() throws SQLException {
        List<Motores> motor = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                motor.add(mapRow(rs));
            }
        }
        return motor;
    }

    private Motores mapRow(ResultSet rs) throws  SQLException{
        Motores m = new Motores();
        m.setIdMotor(rs.getInt("idMotor"));
        m.setNombre(rs.getString("nombre"));
        m.setCilindrada(rs.getString("cilindrada"));
        return m;
    }

}
