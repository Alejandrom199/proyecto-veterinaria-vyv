package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for managing SQL queries loaded from a properties file.
 * Implements the Singleton pattern to provide centralized access to all queries.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class QueryManager {
    private static final Properties queries = new Properties();

    static {
        try (InputStream input = QueryManager.class.getClassLoader().getResourceAsStream("queries.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró queries.properties");
            }
            queries.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar queries.properties", e);
        }
    }

    /**
     * Gets a SQL query by its key.
     *
     * @param key the key identifying the query
     * @return the SQL query string
     * @throws RuntimeException if the query key is not found
     */
    public static String getQuery(String key) {
        String query = queries.getProperty(key);
        if (query == null) {
            throw new RuntimeException("Query no encontrado: " + key);
        }
        return query;
    }
}