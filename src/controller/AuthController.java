package controller;

import model.entities.Usuario;
import model.exceptions.AuthException;
import model.exceptions.BusinessException;
import model.services.AuthService;

/**
 * Controlador para manejar las operaciones de autenticación de usuarios.
 * Actúa como intermediario entre la capa de vista y los servicios de autenticación.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor que inicializa el controlador con un servicio de autenticación.
     *
     * @param authService el servicio de autenticación a utilizar
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Autentica un usuario con las credenciales proporcionadas.
     *
     * @param nombreUsuario el nombre de usuario
     * @param contrasena la contraseña del usuario
     * @return true si la autenticación es exitosa, false en caso contrario
     */
// Método para autenticar al usuario
    public boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        try {
            return authService.autenticarUsuario(nombreUsuario, contrasena);
        } catch (AuthException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario el usuario a registrar
     */
    public void registrarUsuario(Usuario usuario) {
        try {
            authService.registrarUsuario(usuario);
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Verifica si un usuario está autenticado en el sistema.
     *
     * @param nombreUsuario el nombre de usuario a verificar
     * @return true si el usuario está autenticado, false en caso contrario
     */
    public boolean estaAutenticado(String nombreUsuario) {
        try {
            return authService.estaAutenticado(nombreUsuario);
        } catch (AuthException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
