package model.exceptions;

/**
 * Excepción lanzada cuando ocurren errores de persistencia de datos.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class PersistenceException extends Exception {

    /**
     * Constructor con mensaje de error.
     *
     * @param message Mensaje descriptivo del error
     */
    public  PersistenceException(String message){
        super(message);
    }

    /**
     * Constructor con mensaje de error y causa.
     *
     * @param message Mensaje descriptivo del error
     * @param cause   Excepción original que causó el error
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}