package model.services;

import model.entities.Usuario;
import model.exceptions.AuthException;
import model.exceptions.BusinessException;

/**
 * Interfaz para el servicio de autenticación de usuarios.
 * Proporciona métodos para manejar la autenticación y registro de usuarios.
 *
 * @author Lenny Borbor
 * @version 1.0
 */
public interface AuthService {
    /**
     * Autentica un usuario con nombre de usuario y contraseña.
     *
     * @param nombreUsuario el nombre de usuario
     * @param contrasena la contraseña del usuario
     * @return true si la autenticación es exitosa, false en caso contrario
     * @throws AuthException si ocurre un error durante la autenticación
     */
    boolean autenticarUsuario(String nombreUsuario, String contrasena) throws AuthException;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario el objeto Usuario a registrar
     * @throws BusinessException si ocurre un error durante el registro
     */
    void registrarUsuario(Usuario usuario) throws BusinessException;

    /**
     * Verifica si un usuario está autenticado.
     *
     * @param nombreUsuario el nombre de usuario a verificar
     * @return true si el usuario está autenticado, false en caso contrario
     * @throws AuthException si ocurre un error durante la verificación
     */
    boolean estaAutenticado(String nombreUsuario) throws AuthException;
}
