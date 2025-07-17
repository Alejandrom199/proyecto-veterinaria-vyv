package model.exceptions;

/**
 * Excepción lanzada cuando ocurren errores de negocio.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public class BusinessException extends Exception {
    /**
     * Constructor con mensaje de error.
     *
     * @param message Mensaje descriptivo del error
     */
    public BusinessException(String message) {
        super(message);
    }
}
