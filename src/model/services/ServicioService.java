package model.services;

import model.entities.Servicio;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de servicios.
 * Proporciona métodos CRUD para los servicios ofrecidos por la veterinaria.
 *
 * @author Mariana Yagual
 * @version 1.0
 */
public interface ServicioService {
    /**
     * Guarda un nuevo servicio en el sistema.
     *
     * @param servicio el objeto Servicio a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarServicio(Servicio servicio) throws BusinessException;

    /**
     * Obtiene todos los servicios registrados en el sistema.
     *
     * @return lista de todos los servicios
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Servicio> obtenerTodosLosServicios() throws BusinessException;

    /**
     * Busca un servicio por su ID.
     *
     * @param id el ID del servicio a buscar
     * @return el servicio encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Servicio buscarServicioPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de un servicio existente.
     *
     * @param servicio el objeto Servicio con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarServicio(Servicio servicio) throws BusinessException;

    /**
     * Elimina un servicio del sistema.
     *
     * @param id el ID del servicio a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarServicio(int id) throws BusinessException;
}