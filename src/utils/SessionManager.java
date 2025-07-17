package utils;

import java.io.*;

/**
 * Utility class for managing user session persistence.
 * Handles saving and loading user credentials between application sessions.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class SessionManager {
    private static final String SESSION_FILE = "session.dat";

    /**
     * Saves user credentials to a file for persistent session.
     *
     * @param usuario the username to save
     * @param contrasena the password to save
     */
    public static void guardarCredenciales(String usuario, String contrasena) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SESSION_FILE))) {

            oos.writeObject(usuario);
            oos.writeObject(contrasena);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads saved user credentials from file.
     *
     * @return an array containing username and password, or null if no session exists
     */
    public static String[] cargarCredenciales() {
        File file = new File(SESSION_FILE);
        if (!file.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SESSION_FILE))) {

            return new String[]{
                    (String) ois.readObject(),
                    (String) ois.readObject()
            };

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Deletes the saved session file.
     */
    public static void eliminarSesion() {
        File file = new File(SESSION_FILE);
        if (file.exists()) file.delete();
    }
}