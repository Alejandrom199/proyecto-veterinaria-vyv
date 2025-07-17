package controller;

import model.entities.Servicio;
import model.exceptions.BusinessException;
import model.services.ServicioService;
import model.services.impl.ServicioServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con servicios.
 * Gestiona la interacción entre la vista y los servicios de atención.
 *
 * @author Mariana Yagual
 * @version 1.0
 * @since 2023
 */
public class ServicioController {

    private final ServicioService servicioService;

    /**
     * Constructor que inicializa el controlador con un servicio de atención.
     *
     * @param servicioService el servicio de atención a utilizar
     */
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    /**
     * Guarda un nuevo servicio en el sistema.
     *
     * @param servicio el servicio a guardar
     */
    public void guardarServicio(Servicio servicio) {
        try {
            servicioService.guardarServicio(servicio);
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los servicios registrados en el sistema.
     *
     * @return lista de todos los servicios
     */
    public List<Servicio> obtenerTodosServicios() {
        try {
            return servicioService.obtenerTodosLosServicios();
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca un servicio por su ID.
     *
     * @param id el ID del servicio a buscar
     * @return el servicio encontrado o null si no existe
     */
    public Servicio buscarServicioPorId(int id) {
        try {
            return servicioService.buscarServicioPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un servicio existente.
     *
     * @param servicio el servicio con los datos actualizados
     */
    public void actualizarServicio(Servicio servicio) {
        try {
            servicioService.actualizarServicio(servicio);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina un servicio del sistema.
     *
     * @param id el ID del servicio a eliminar
     */
    public void eliminarServicio(int id) {
        try {
            servicioService.eliminarServicio(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}