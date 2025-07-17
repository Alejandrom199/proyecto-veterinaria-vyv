package model.services;

import model.entities.Cita;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de citas.
 * Proporciona métodos CRUD para las citas veterinarias.
 *
 * @author Saúl Maldonado
 * @version 1.0
 */
public interface CitaService {
    /**
     * Guarda una nueva cita en el sistema.
     *
     * @param cita el objeto Cita a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarCita(Cita cita) throws BusinessException;

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return lista de todas las citas
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Cita> obtenerTodasCitas() throws BusinessException;

    /**
     * Busca una cita por su ID.
     *
     * @param id el ID de la cita a buscar
     * @return la cita encontrada
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Cita buscarCitaPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param cita el objeto Cita con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarCita(Cita cita) throws BusinessException;

    /**
     * Elimina una cita del sistema.
     *
     * @param id el ID de la cita a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarCita(int id) throws BusinessException;
}
