package controller;

import model.entities.Usuario;
import model.exceptions.BusinessException;
import model.services.UsuarioService;
import model.services.impl.UsuarioServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con usuarios.
 * Gestiona la interacción entre la vista y los servicios de usuarios.
 *
 * @author Lenny Borbor
 * @version 1.0
 * @since 2023
 */
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Constructor que inicializa el controlador con un servicio de usuarios.
     *
     * @param usuarioService el servicio de usuarios a utilizar
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario el usuario a guardar
     */
    public void guardarUsuario(Usuario usuario) {
        try {
            usuarioService.guardarUsuario(usuario);
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return lista de todos los usuarios
     */
    public List<Usuario> obtenerTodosUsuarios() {
        try {
            return usuarioService.obtenerTodosLosUsuarios();
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario a buscar
     * @return el usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorId(int id) {
        try {
            return usuarioService.buscarUsuarioPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param nombreUsuario el nombre de usuario a buscar
     * @return el usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        try {
            return usuarioService.buscarPorNombreUsuario(nombreUsuario);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario el usuario con los datos actualizados
     */
    public void actualizarUsuario(Usuario usuario) {
        try {
            usuarioService.actualizarUsuario(usuario);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
