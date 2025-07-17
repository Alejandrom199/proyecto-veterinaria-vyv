package model.services.impl;

import model.dao.ServicioDAO;
import model.entities.Servicio;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.ServicioService;

import java.util.List;

/**
 * Implementación del servicio para gestión de servicios de la veterinaria.
 * Proporciona operaciones CRUD para servicios.
 *
 * @author Mariana Yagual
 * @version 1.0
 * @since 2023
 */
public class ServicioServiceImpl implements ServicioService {

    private final ServicioDAO servicioDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de servicios.
     *
     * @param servicioDAO el DAO que manejará las operaciones de persistencia
     */
    public ServicioServiceImpl(ServicioDAO servicioDAO) {
        this.servicioDAO = servicioDAO;
    }

    /**
     * Guarda un nuevo servicio después de validar sus datos.
     *
     * @param servicio el servicio a guardar
     * @throws BusinessException si el servicio no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarServicio(Servicio servicio) throws BusinessException {
        validarServicio(servicio);
        try {
            servicioDAO.guardar(servicio);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar el servicio: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los servicios registrados en el sistema.
     *
     * @return lista de todos los servicios
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Servicio> obtenerTodosLosServicios() throws BusinessException {
        try {
            return servicioDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener servicios: " + e.getMessage());
        }
    }

    /**
     * Busca un servicio por su ID en la base de datos.
     *
     * @param id El ID del servicio a buscar
     * @return El servicio encontrado
     * @throws BusinessException Si el servicio no existe o hay error de persistencia
     */
    @Override
    public Servicio buscarServicioPorId(int id) throws BusinessException {
        try {
            return servicioDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar servicio: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un servicio existente.
     *
     * @param servicio el servicio con los datos actualizados
     * @throws BusinessException si el servicio no es válido o hay error de persistencia
     */
    @Override
    public void actualizarServicio(Servicio servicio) throws BusinessException {
        validarServicio(servicio);
        try {
            servicioDAO.actualizar(servicio);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar servicio: " + e.getMessage());
        }
    }

    /**
     * Elimina un servicio existente.
     *
     * @param id El ID del servicio a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarServicio(int id) throws BusinessException {
        try {
            servicioDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar servicio: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un servicio sean correctos.
     * (Implementación pendiente de validaciones específicas)
     *
     * @param servicio el servicio a validar
     * @throws BusinessException si el servicio no cumple con las validaciones requeridas
     */
    private void validarServicio(Servicio servicio) throws BusinessException {

    }
}
