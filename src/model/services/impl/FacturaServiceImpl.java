package model.services.impl;

import model.dao.FacturaDAO;
import model.entities.Factura;
import model.exceptions.BusinessException;
import model.exceptions.PersistenceException;
import model.services.FacturaService;

import java.util.List;

/**
 * Implementación del servicio para gestión de facturas de la veterinaria.
 * Proporciona operaciones CRUD para facturas.
 *
 * @author Anthony López
 * @version 1.0
 * @since 2023
 */
public class FacturaServiceImpl implements FacturaService {

    private final FacturaDAO facturaDAO;

    /**
     * Constructor que inicializa el servicio con un DAO de facturas.
     *
     * @param facturaDAO el DAO que manejará las operaciones de persistencia
     */
    public FacturaServiceImpl(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    /**
     * Guarda una nueva factura después de validar sus datos.
     *
     * @param factura la factura a guardar
     * @throws BusinessException si la factura no cumple validaciones o hay error de persistencia
     */
    @Override
    public void guardarFactura(Factura factura) throws BusinessException {
        validarFactura(factura);
        try {
            facturaDAO.guardar(factura);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al guardar la factura: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las facturas registradas en el sistema.
     *
     * @return lista de todas las facturas
     * @throws BusinessException si ocurre un error al acceder a los datos
     */
    @Override
    public List<Factura> obtenerTodasLasFacturas() throws BusinessException {
        try {
            return facturaDAO.obtenerTodos();
        } catch (PersistenceException e) {
            throw new BusinessException("Error al obtener facturas: " + e.getMessage());
        }
    }

    /**
     * Busca una factura por su ID en la base de datos.
     *
     * @param id El ID de la factura a buscar
     * @return La factura encontrada
     * @throws BusinessException Si la factura no existe o hay error de persistencia
     */
    @Override
    public Factura buscarFacturaPorId(int id) throws BusinessException {
        try {
            return facturaDAO.buscarPorId(id);
        } catch (PersistenceException e) {
            throw new BusinessException("Error al buscar cita: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de una factura existente.
     *
     * @param factura la factura con los datos actualizados
     * @throws BusinessException si la factura no es válida o hay error de persistencia
     */
    @Override
    public void actualizarFactura(Factura factura) throws BusinessException {
        validarFactura(factura);
        try {
            facturaDAO.actualizar(factura);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al actualizar factura: " + e.getMessage());
        }
    }

    /**
     * Elimina una factura existente.
     *
     * @param id El ID de la factura a eliminar
     * @throws BusinessException Si falla la operación en la base de datos
     */
    @Override
    public void eliminarFactura(int id) throws BusinessException {
        try {
            facturaDAO.eliminar(id);
        }
        catch (PersistenceException e) {
            throw new BusinessException("Error al eliminar factura: " + e.getMessage());
        }
    }

    /**
     * Valida que los datos básicos de una factura sean correctos.
     * (Implementación pendiente de validaciones específicas)
     *
     * @param factura la factura a validar
     * @throws BusinessException si la factura no cumple con las validaciones requeridas
     */
    private void validarFactura(Factura factura) throws BusinessException {

    }
}
