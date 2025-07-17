package controller;

import model.entities.Cita;
import model.services.CitaService;
import model.exceptions.BusinessException;

import javax.swing.*;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con citas médicas.
 * Gestiona la interacción entre la vista y los servicios de citas.
 *
 * @author Saúl Maldonado
 * @version 1.0
 * @since 2023
 */
public class CitaController {

    private final CitaService citaService;

    /**
     * Constructor que inicializa el controlador con un servicio de citas.
     *
     * @param citaService el servicio de citas a utilizar
     */
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    /**
     * Guarda una nueva cita en el sistema.
     *
     * @param cita la cita a guardar
     */
    public void guardarCita(Cita cita) {
        try {
            citaService.guardarCita(cita);
        }
        catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en Cita",  JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return lista de todas las citas
     */
    public List<Cita> obtenerTodasCitas() {
        try {
            return citaService.obtenerTodasCitas();
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en Cita",  JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    /**
     * Busca una cita por su ID.
     *
     * @param id el ID de la cita a buscar
     * @return la cita encontrada o null si no existe
     */
    public Cita buscarCitaPorId(int id) {
        try {
            return citaService.buscarCitaPorId(id);
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en Cita",  JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Actualiza los datos de una cita existente.
     *
     * @param cita la cita con los datos actualizados
     */
    public void actualizarCita(Cita cita) {
        try {
            citaService.actualizarCita(cita);
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en Cita",  JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina una cita del sistema.
     *
     * @param id el ID de la cita a eliminar
     */
    public void eliminarCita(int id) {
        try {
            citaService.eliminarCita(id);
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en Cita",  JOptionPane.ERROR_MESSAGE);
        }
    }
}