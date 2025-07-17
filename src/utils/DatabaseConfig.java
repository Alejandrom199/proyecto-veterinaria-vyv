package utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for loading and accessing database configuration properties.
 * Reads configuration from a properties file in the classpath.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Archivo config.properties no encontrado");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
    }

    /**
     * Gets the database URL from configuration.
     *
     * @return the database URL
     */
    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    /**
     * Gets the database username from configuration.
     *
     * @return the database username
     */
    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    /**
     * Gets the database password from configuration.
     *
     * @return the database password
     */
    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    /**
     * Gets the database driver class name from configuration.
     *
     * @return the database driver class name
     */
    public static String getDbDriver() {
        return properties.getProperty("db.driver");
    }
}