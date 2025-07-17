package controller;

import model.dto.MedicamentoMasVendidoDTO;
import model.entities.Medicamento;
import model.exceptions.BusinessException;
import model.services.MedicamentoService;
import model.services.impl.MedicamentoServiceImpl;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con medicamentos.
 * Gestiona la interacción entre la vista y los servicios de medicamentos.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 * @since 2023
 */
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    /**
     * Constructor que inicializa el controlador con un servicio de medicamentos.
     *
     * @param medicamentoService el servicio de medicamentos a utilizar
     */
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    /**
     * Guarda un nuevo medicamento en el sistema.
     *
     * @param medicamento el medicamento a guardar
     */
    public void guardarMedicamento(Medicamento medicamento) {
        try {
            medicamentoService.guardarMedicamento(medicamento);
        }
        catch (BusinessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los medicamentos registrados en el sistema.
     *
     * @return lista de todos los medicamentos
     */
    public List<Medicamento> obtenerTodosMedicamentos() {
        try {
            return medicamentoService.obtenerTodosLosMedicamentos();
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Busca un medicamento por su ID.
     *
     * @param id el ID del medicamento a buscar
     * @return el medicamento encontrado o null si no existe
     */
    public Medicamento buscarMedicamentoPorId(int id) {
        try {
            return medicamentoService.buscarMedicamentoPorId(id);
        } catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un medicamento existente.
     *
     * @param medicamento el medicamento con los datos actualizados
     */
    public void actualizarMedicamento(Medicamento medicamento) {
        try {
            medicamentoService.actualizarMedicamento(medicamento);
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina un medicamento del sistema.
     *
     * @param id el ID del medicamento a eliminar
     */
    public void eliminarMedicamento(int id) {
        try {
            medicamentoService.eliminarMedicamento(id);
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene un reporte de los medicamentos más vendidos.
     *
     * @return lista de DTOs con información de medicamentos más vendidos
     */
    public List<MedicamentoMasVendidoDTO> obtenerMedicamentosMasVendidos() {
        try {
            return medicamentoService.buscarMedicamentosMasVendidos();
        }
        catch (BusinessException e) {
            System.err.println("Error: " + e.getMessage());
            return List.of();
        }
    }

}
