package model.services.impl;

import model.dao.DetalleFacturaDAO;
import model.entities.DetalleFactura;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.DetalleFacturaService;

import java.util.List;

/**
 * Implementación del servicio para gestión de detalles de factura.
 * Proporciona operaciones CRUD para detalles de factura con validaciones básicas.
 *
 * @author Anthony López
 * @version 1.0
 * @since 2023
 */
public class DetalleFacturaServiceImpl implements DetalleFacturaService {

    private final DetalleFacturaDAO detalleFacturaDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de detalles de factura.
     *
     * @param detalleFacturaDAO el DAO que manejará las operaciones de persistencia
     */
    public DetalleFacturaServiceImpl(DetalleFacturaDAO detalleFacturaDAO) {
        this.detalleFacturaDAO = detalleFacturaDAO;
    }

    /**
     * Guarda un nuevo detalle de factura después de validar sus datos.
     *
     * @param detalleFactura el detalle a guardar
     * @throws BusinessException si el detalle no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarDetalleFactura(DetalleFactura detalleFactura) throws BusinessException {
        validarDetalleFactura(detalleFactura);
        try {
            detalleFacturaDAO.guardar(detalleFactura);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar el detalle factura: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los detalles de factura registrados en el sistema.
     *
     * @return lista de todos los detalles de factura
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<DetalleFactura> obtenerTodosLosDetallesFactura() throws BusinessException {
        try {
            return detalleFacturaDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener detalles factura: " + e.getMessage());
        }
    }

    /**
     * Busca un detalle de factura por su ID en la base de datos.
     *
     * @param id El ID del detalle a buscar
     * @return El detalle encontrado
     * @throws BusinessException Si el detalle no existe o hay error de persistencia
     */
    @Override
    public DetalleFactura buscarDetalleFacturaPorId(int id) throws BusinessException {
        try {
            return detalleFacturaDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar cita: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los detalles de factura asociados a una factura específica.
     *
     * @param idFactura El ID de la factura a buscar
     * @return Lista de detalles de factura asociados
     * @throws BusinessException Si ocurre un error al acceder a los datos
     */
    @Override
    public List<DetalleFactura> obtenerDetalleFacturaPorIdFactura(int idFactura) throws BusinessException {
        try{
            return detalleFacturaDAO.obtenerPorIdFactura(idFactura);
        }
        catch (PersistenceException e){
            throw new BusinessException("Error al buscar detalles factura por id factura: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de un detalle de factura existente.
     *
     * @param detalleFactura el detalle con los datos actualizados
     * @throws BusinessException si el detalle no es válido o hay error de persistencia
     */
    @Override
    public void actualizarDetalleFactura(DetalleFactura detalleFactura) throws BusinessException {
        validarDetalleFactura(detalleFactura);
        try {
            detalleFacturaDAO.actualizar(detalleFactura);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar detalle factura: " + e.getMessage());
        }
    }

    /**
     * Elimina un detalle de factura existente.
     *
     * @param id El ID del detalle a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarDetalleFactura(int id) throws BusinessException {
        try {
            detalleFacturaDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar detalle factura: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de un detalle de factura sean correctos.
     *
     * @param detalleFactura el detalle a validar
     * @throws BusinessException si el detalle no cumple con las validaciones requeridas
     */
    private void validarDetalleFactura(DetalleFactura detalleFactura) throws BusinessException {
        if(detalleFactura.getTipo() == null){
            throw new BusinessException("El tipo de detalle factura no puede ser nulo");
        }
    }
}
