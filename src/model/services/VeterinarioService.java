package model.services;

import model.entities.Veterinario;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de veterinarios.
 * Proporciona métodos CRUD para los veterinarios del sistema.
 *
 * @author Lenny Borbor
 * @version 1.0
 */
public interface VeterinarioService {
    /**
     * Guarda un nuevo veterinario en el sistema.
     *
     * @param veterinario el objeto Veterinario a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarVeterinario(Veterinario veterinario) throws BusinessException;

    /**
     * Obtiene todos los veterinarios registrados en el sistema.
     *
     * @return lista de todos los veterinarios
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Veterinario> obtenerTodosLosVeterinarios() throws BusinessException;

    /**
     * Busca un veterinario por su ID.
     *
     * @param id el ID del veterinario a buscar
     * @return el veterinario encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Veterinario buscarVeterinarioPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario el objeto Veterinario con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarVeterinario(Veterinario veterinario) throws BusinessException;

    /**
     * Elimina un veterinario del sistema.
     *
     * @param id el ID del veterinario a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarVeterinario(int id) throws BusinessException;
}