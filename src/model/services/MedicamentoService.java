package model.services;

import model.dto.MedicamentoMasVendidoDTO;
import model.entities.Medicamento;
import model.exceptions.BusinessException;

import java.util.List;

/**
 * Interfaz para el servicio de gestión de medicamentos.
 * Proporciona métodos CRUD para los medicamentos y reportes.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 */
public interface MedicamentoService {
    /**
     * Guarda un nuevo medicamento en el sistema.
     *
     * @param medicamento el objeto Medicamento a guardar
     * @throws BusinessException si ocurre un error durante el guardado
     */
    void guardarMedicamento(Medicamento medicamento) throws BusinessException;

    /**
     * Obtiene todos los medicamentos registrados en el sistema.
     *
     * @return lista de todos los medicamentos
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<Medicamento> obtenerTodosLosMedicamentos() throws BusinessException;

    /**
     * Busca un medicamento por su ID.
     *
     * @param id el ID del medicamento a buscar
     * @return el medicamento encontrado
     * @throws BusinessException si ocurre un error durante la búsqueda
     */
    Medicamento buscarMedicamentoPorId(int id) throws BusinessException;

    /**
     * Actualiza los datos de un medicamento existente.
     *
     * @param medicamento el objeto Medicamento con los datos actualizados
     * @throws BusinessException si ocurre un error durante la actualización
     */
    void actualizarMedicamento(Medicamento medicamento) throws BusinessException;

    /**
     * Elimina un medicamento del sistema.
     *
     * @param id el ID del medicamento a eliminar
     * @throws BusinessException si ocurre un error durante la eliminación
     */
    void eliminarMedicamento(int id) throws BusinessException;

    /**
     * Obtiene un reporte de los medicamentos más vendidos.
     *
     * @return lista de DTOs con información de medicamentos más vendidos
     * @throws BusinessException si ocurre un error durante la consulta
     */
    List<MedicamentoMasVendidoDTO> buscarMedicamentosMasVendidos() throws BusinessException;
}