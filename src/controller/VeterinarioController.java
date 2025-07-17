package controller;

import model.entities.Veterinario;
import model.exceptions.BusinessException;
import model.services.VeterinarioService;
import model.services.impl.VeterinarioServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con veterinarios.
 * Gestiona la interacción entre la vista y los servicios de veterinarios.
 *
 * @author Lenny Borbor
 * @version 1.0
 * @since 2023
 */
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    /**
     * Constructor que inicializa el controlador con un servicio de veterinarios.
     *
     * @param veterinarioService el servicio de veterinarios a utilizar
     */
    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    /**
     * Guarda un nuevo veterinario en el sistema.
     *
     * @param veterinario el veterinario a guardar
     */
    public void guardarVeterinario(Veterinario veterinario) {
        try {
            veterinarioService.guardarVeterinario(veterinario);
        } catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los veterinarios registrados en el sistema.
     *
     * @return lista de todos los veterinarios
     */
    public List<Veterinario> obtenerTodosVeterinarios() {
        try {
            return veterinarioService.obtenerTodosLosVeterinarios();
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca un veterinario por su ID.
     *
     * @param id el ID del veterinario a buscar
     * @return el veterinario encontrado o null si no existe
     */
    public Veterinario buscarVeterinarioPorId(int id) {
        try {
            return veterinarioService.buscarVeterinarioPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario el veterinario con los datos actualizados
     */
    public void actualizarVeterinario(Veterinario veterinario) {
        try {
            veterinarioService.actualizarVeterinario(veterinario);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina un veterinario del sistema.
     *
     * @param id el ID del veterinario a eliminar
     */
    public void eliminarVeterinario(int id) {
        try {
            veterinarioService.eliminarVeterinario(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

