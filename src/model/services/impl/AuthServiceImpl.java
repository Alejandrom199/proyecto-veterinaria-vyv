package model.services.impl;

import model.dao.UsuarioDAO;
import model.entities.Usuario;
import model.exceptions.AuthException;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.AuthService;

/**
 * Implementación del servicio de autenticación para usuarios del sistema veterinario.
 * Proporciona métodos para autenticación, registro y verificación de estado de usuarios.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class AuthServiceImpl implements AuthService {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de usuarios.
     *
     * @param usuarioDAO el DAO que manejará las operaciones de persistencia de usuarios
     */
    public AuthServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Autentica un usuario mediante nombre de usuario y contraseña.
     *
     * @param nombreUsuario el nombre de usuario a autenticar
     * @param contrasena la contraseña a verificar
     * @return true si la autenticación es exitosa
     * @throws AuthException si las credenciales son incorrectas o hay error de persistencia
     */
    @Override
    public boolean autenticarUsuario(String nombreUsuario, String contrasena) throws AuthException {
        try{
            Usuario usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);

            if (usuario == null) {
                throw new AuthException("Usuario no encontrado");
            }

            if (!usuario.getContrasena().equals(contrasena)) {
                throw new AuthException("Contraseña incorrecta");
            }

            return true;
        }
        catch (PersistenceException e){
            throw new AuthException("Error al iniciar sesión: " + e.getMessage());
        }
    }

    /**
     * Registra un nuevo usuario en el sistema después de validar sus datos.
     *
     * @param usuario el usuario a registrar
     * @throws BusinessException si el usuario no cumple validaciones o hay error de persistencia
     */
    @Override
    public void registrarUsuario(Usuario usuario) throws BusinessException {
        try {
            if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
                throw new BusinessException("El nombre de usuario es requerido");
            }

            if (usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()) {
                throw new BusinessException("La contraseña es requerida");
            }

            if (usuarioDAO.buscarPorNombreUsuario(usuario.getNombreUsuario()) != null) {
                throw new BusinessException("El nombre de usuario ya está registrado");
            }

            usuarioDAO.guardar(usuario);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al registrar usuario: " + e.getMessage());
        }
    }

    /**
     * Verifica si un usuario está autenticado en el sistema.
     *
     * @param nombreUsuario el nombre de usuario a verificar
     * @return true si el usuario existe en el sistema
     * @throws AuthException si ocurre un error al acceder a los datos
     */
    @Override
    public boolean estaAutenticado(String nombreUsuario) throws AuthException {
        try {
            return usuarioDAO.buscarPorNombreUsuario(nombreUsuario) != null;
        }
        catch (PersistenceException e){
            throw new AuthException("Error un usuario ya esta autenticado. "+e.getMessage());
        }
    }
}
