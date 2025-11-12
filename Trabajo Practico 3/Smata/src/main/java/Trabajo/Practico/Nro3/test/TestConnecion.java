package Trabajo.Practico.Nro3.test;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestConnecion {

    public static void main(String[] args) {

        Properties props = new Properties();
        //creamos un objeto de Properties para cargar el fichero de configuración
        try (InputStream in = TestConnecion.class //obtenemos el objeto Class de esta clase
                                .getClassLoader() //obtenemos el ClassLoader que es el responsable
                                                  //de cargar clases y recursos en tiempo de ejecución
                                .getResourceAsStream("application.properties")) {//busca el archivo que
                                    //le pasamos como parámetro y lo devuelve como un flujo de bytes 
            if(in==null){
                System.err.println("No se encontró el application.properties en el classpath");
                return;
            }
            props.load(in);
        } catch (Exception e) {
            System.err.println("Error cargando propiedades: " + e.getMessage());
            return;
        }

        //creamos la configuración del pool
        HikariConfig config = new HikariConfig();

        //leemos la URL de la DB 
        config.setJdbcUrl(props.getProperty("spring.datasource.url"));
        //obtenemos usuario y contraseña para conectarnos
        config.setUsername(props.getProperty("spring.datasource.username"));
        config.setPassword(props.getProperty("spring.datasource.password"));

        //creamos el DataSource con el pool de conexiones y probamos la conexión
        try (HikariDataSource ds = new HikariDataSource(config);
             Connection conn = ds.getConnection()) { //obtenemos la conexión
            
            if(conn.isValid(2)){ //comprueba si la conexión es válida
                //el parámetro indica la cantidad de segundos que el Driver JDBC va a esperar
                //para confirmar la conexión con el servidor
                System.out.println("Conexión exitosa a:" 
                                    + conn.getMetaData().getURL());
                //con getMetaData() obtenemos la información sobre la conexión activa
                //getUrl() retorna la URL de la conexión utilizada
                //la imprimimos para verificar a qué base de datos nos conectamos.
            } else {
                System.err.println("Conexión establecida pero no válida");
            }

        } catch (Exception e) {
            System.err.println("No se pudo conectar" + e.getMessage());
        }
        
    }
}
