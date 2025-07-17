package model.services.impl;

import model.dao.UsuarioDAO;
import model.entities.Usuario;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.UsuarioService;

import java.util.List;

/**
 * Implementación del servicio para gestión de usuarios del sistema.
 * Proporciona operaciones CRUD para usuarios con validaciones básicas.
 *
 * @author Lenny Borbor
 * @version 1.0
 * @since 2023
 */
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de usuarios.
     *
     * @param usuarioDAO el DAO que manejará las operaciones de persistencia
     */
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * Guarda un nuevo usuario después de validar sus datos.
     *
     * @param usuario el usuario a guardar
     * @throws BusinessException si el usuario no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarUsuario(Usuario usuario) throws BusinessException {
        validarUsuario(usuario);
        try {
            usuarioDAO.guardar(usuario);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return lista de todos los usuarios
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Usuario> obtenerTodosLosUsuarios() throws BusinessException {
        try {
            return usuarioDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener usuarios: " + e.getMessage());
        }
    }

    /**
     * Busca un usuario por su ID en la base de datos.
     *
     * @param id El ID del usuario a buscar
     * @return El usuario encontrado
     * @throws BusinessException Si el usuario no existe o hay error de persistencia
     */
    @Override
    public Usuario buscarUsuarioPorId(int id) throws BusinessException {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar usuario: " + e.getMessage());
        }
    }

    /**
     * Busca un usuario por su nombre de usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario a buscar
     * @return El usuario encontrado
     * @throws BusinessException Si el usuario no existe o hay error de persistencia
     */
    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) throws BusinessException {
        try {
            return usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar usuario: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario el usuario con los datos actualizados
     * @throws BusinessException si el usuario no es válido o hay error de persistencia
     */
    @Override
    public void actualizarUsuario(Usuario usuario) throws BusinessException {
        validarUsuario(usuario);
        try {
            usuarioDAO.actualizar(usuario);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar usuario: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un usuario sean correctos.
     *
     * @param usuario el usuario a validar
     * @throws BusinessException si el usuario no cumple con las validaciones requeridas
     */
    private void validarUsuario(Usuario usuario) throws BusinessException {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().equals("")) {
            throw new BusinessException("El Nombre de usuario es requerido");
        }

    }
}
