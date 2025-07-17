package model.database;

import utils.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para manejar conexiones a la base de datos.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class Conexion {

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Conexión activa a la base de datos
     * @throws SQLException Si ocurre un error al establecer la conexión
     * @throws ClassNotFoundException Si no se encuentra el driver JDBC
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Cargar el driver de MySQL
        Class.forName(DatabaseConfig.getDbDriver());

        // Obtener parámetros desde la configuración
        String url = DatabaseConfig.getDbUrl();
        String user = DatabaseConfig.getDbUser();
        String password = DatabaseConfig.getDbPassword();

        return DriverManager.getConnection(url, user, password);
    }
}