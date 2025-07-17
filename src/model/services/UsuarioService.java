package model.services;

import model.entities.Usuario;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de usuarios.
 * Proporciona métodos CRUD para los usuarios del sistema.
 *
 * @author Lenny Borbor
 * @version 1.0
 */
public interface UsuarioService {
    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario el objeto Usuario a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarUsuario(Usuario usuario) throws BusinessException;

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     *
     * @return lista de todos los usuarios
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Usuario> obtenerTodosLosUsuarios() throws BusinessException;

    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario a buscar
     * @return el usuario encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Usuario buscarUsuarioPorId(int id) throws BusinessException;

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param nombreUsuario el nombre de usuario a buscar
     * @return el usuario encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Usuario buscarPorNombreUsuario(String nombreUsuario) throws BusinessException;

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario el objeto Usuario con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarUsuario(Usuario usuario) throws BusinessException;
}