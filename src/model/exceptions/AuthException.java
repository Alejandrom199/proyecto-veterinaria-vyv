// AuthException.java
package model.exceptions;

/**
 * Excepción lanzada cuando ocurren errores de autenticación.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class AuthException extends Exception {
    /**
     * Constructor con mensaje de error.
     *
     * @param message Mensaje descriptivo del error
     */
    public AuthException(String message) {
        super(message);
    }
}