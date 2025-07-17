package model.services.impl;

import model.dao.MedicamentoDAO;
import model.dto.MedicamentoMasVendidoDTO;
import model.entities.Medicamento;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.MedicamentoService;

import java.util.List;

/**
 * Implementación del servicio para gestión de medicamentos en la veterinaria.
 * Proporciona operaciones CRUD para medicamentos y reportes de ventas.
 *
 * @author Jeremy Alvarado
 * @version 1.0
 * @since 2023
 */
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoDAO medicamentoDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de medicamentos.
     *
     * @param medicamentoDAO el DAO que manejará las operaciones de persistencia
     */
    public MedicamentoServiceImpl(MedicamentoDAO medicamentoDAO) {
        this.medicamentoDAO = medicamentoDAO;
    }

    /**
     * Guarda un nuevo medicamento después de validar sus datos.
     *
     * @param medicamento el medicamento a guardar
     * @throws BusinessException si el medicamento no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarMedicamento(Medicamento medicamento) throws BusinessException {
        validarMedicamento(medicamento);
        try {
            medicamentoDAO.guardar(medicamento);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar el medicamento: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los medicamentos registrados en el sistema.
     *
     * @return lista de todos los medicamentos
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Medicamento> obtenerTodosLosMedicamentos() throws BusinessException {
        try {
            return medicamentoDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener medicamentos: " + e.getMessage());
        }
    }

    /**
     * Busca un medicamento por su ID en la base de datos.
     *
     * @param id El ID del medicamento a buscar
     * @return El medicamento encontrado
     * @throws BusinessException Si el medicamento no existe o hay error de persistencia
     */
    @Override
    public Medicamento buscarMedicamentoPorId(int id) throws BusinessException {
        try {
            return medicamentoDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar medicamento: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un medicamento existente.
     *
     * @param medicamento el medicamento con los datos actualizados
     * @throws BusinessException si el medicamento no es válido o hay error de persistencia
     */
    @Override
    public void actualizarMedicamento(Medicamento medicamento) throws BusinessException {
        validarMedicamento(medicamento);
        try {
            medicamentoDAO.actualizar(medicamento);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar medicamento: " + e.getMessage());
        }
    }

    /**
     * Elimina un medicamento existente.
     *
     * @param id El ID del medicamento a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarMedicamento(int id) throws BusinessException {
        try {
            medicamentoDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar medicamento: " + e.getMessage());
        }
    }

    /**
     * Obtiene un reporte de los medicamentos más vendidos.
     *
     * @return Lista de DTOs con información de medicamentos más vendidos
     * @throws BusinessException Si ocurre un error al generar el reporte
     */
    @Override
    public List<MedicamentoMasVendidoDTO> buscarMedicamentosMasVendidos() throws BusinessException {
        try {
            return medicamentoDAO.buscarMedicamentosMasVendidos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener los medicamentos más vendidos" + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un medicamento sean correctos.
     * (Implementación pendiente de validaciones específicas)
     *
     * @param medicamento el medicamento a validar
     * @throws BusinessException si el medicamento no cumple con las validaciones requeridas
     */
    private void validarMedicamento(Medicamento medicamento) throws BusinessException {

    }
}
